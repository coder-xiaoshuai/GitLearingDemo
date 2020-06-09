package com.example.common;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tu on 4/8/15.
 */
public class DeviceUtils {

	private static final String TAG = DeviceUtils.class.getSimpleName();

	private static int oaidStatus = 0; // 0：未初始化， -1：初始化失败、不支持，1：初始化成功
	private static String oaid = "";
	private static final int oaidMinSdk = 29;

	private DeviceUtils() {
	}

	/**
	 * 获取设备的OAID，如果未获取成功则尝试重新获取
	 */
	public static String getOAID() {
		return OaidUtil.oaid;
	}

	@NonNull
	public static String getAndroidId(Context context) {
		String androidId = "";
		try {
			androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		} catch (Exception e) {
//			Logger.e(TAG, "getAndroidId :: unable to get Android_Id");
			e.printStackTrace(System.err);
		}
		return androidId == null ? "" : androidId;
	}

	@NonNull
	public static String getAndroidIdWithDefault(Context context) {
		String androidId = "";
		try {
			androidId = getAndroidId(context);
		} catch (Exception e) {
//			Logger.e(TAG, "getAndroidId :: unable to get Android_Id");
			e.printStackTrace(System.err);
		}
		return TextUtils.isEmpty(androidId)? "yiduicannotgetid" : androidId;
	}


	/**
	 * 默认返回第一个卡槽的IMEI
	 * @param context	Android Context
	 * @return			返回第一个卡槽的IMEI，如果没有返回null
	 */
	@NonNull
	public static String getIMEI(Context context) {
		return getIMEI(context, 0);
	}

	/**
	 * 获取手机对应卡槽的IMEI
	 * @param context	Android Context
	 * @param slotId 	卡槽的位置，0表示卡1，1表示卡2
	 * @return			返回对应卡槽的IMEI，如果没有返回null
	 */
	@NonNull
	public static String getIMEI(Context context, int slotId) {
		String imei;
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm == null) imei = "";

		if (Build.VERSION.SDK_INT >= 21) {
			// 大于Android 5.0 根据具体版本获取IMEI
			if (Build.VERSION.SDK_INT >= 23 &&
					ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
							!= PackageManager.PERMISSION_GRANTED) {
				// 无权限，直接返回null
				imei = "";
			} else if (Build.VERSION.SDK_INT >= 29) {
				// >= Android 10 无法获取，返回null
				imei = "";
			} else if (Build.VERSION.SDK_INT >= 26) {
				// >= Android 8.0 调用原生方法获取第一个IMEI
				imei = tm.getImei(slotId);
			} else {
				// < Android 8.0
				if (Build.VERSION.SDK_INT >= 23 && tm.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
					// 手机网络制式为GSM
					imei = tm.getDeviceId(slotId);
				} else {
					// 手机网络制式非GSM，通过反射获取(反射需要Android 5.0)
					try {
						Method rGetImei = tm.getClass().getMethod("getImei", int.class);
						imei = (String) rGetImei.invoke(tm, slotId);
					} catch (Exception e) {
//						Logger.d(TAG, "getIMEICompat :: unable to get IMEI using Reflection");
						e.printStackTrace(System.err);
						imei = "";
					}
				}
			}
		} else {
			// < Android 5.0
			if (slotId == 0) {
				// 只能通过此方法获取
				imei = tm.getDeviceId();
			} else {
				// 低版本无有效的方法获取多卡槽IMEI
				imei = "";
			}
		}

		return imei == null ? "" : imei;
	}

    /**
     * 获取手机的MEID
     * @param context   Android Context
     * @param slotId    卡槽的位置，目前仅能够测试卡槽1的MEID（绝大多数手机都最多有一个MEID）
     * @return          返回MEID，如果未能获取，则返回null
     */
    @NonNull
    public static String getMEID(Context context, int slotId) {
    	String meid = null;
        // 6.0+ 无权限直接返回null
        if (Build.VERSION.SDK_INT >= 23 &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
            // 无权限，直接返回空
			meid = "";
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) meid = "";

        try {
            if (Build.VERSION.SDK_INT >= 29) {
				meid = null;
            } else if (Build.VERSION.SDK_INT >= 26) {
				meid = tm.getMeid(slotId);
            } else if (Build.VERSION.SDK_INT >= 21) {
                if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
//                    Logger.d(TAG, "phone is CDMA");
                    if (Build.VERSION.SDK_INT >= 23) {
						meid = tm.getDeviceId(slotId);
                    } else {
						meid = tm.getDeviceId();
                    }
                } else {
//                    Logger.d(TAG, "phone is GSM");
                    meid = getMeidFromReflection();
                }
            } else {
                // 低版本无法获取
				meid = getMeidFromReflection();
            }

        } catch (Exception e) {
//            Logger.d(TAG, "getMEID :: exception while get meid");
            e.printStackTrace(System.err);
        }

        return meid == null ? "" : meid;
    }

    /**
     * 通过反射的方式去获取MEID，此方法可能无法在某些手机上获取到反射方法，建议用作备用方法
     * @return  返回MEID，如果未能获取，则返回null
     */
    @Nullable
    public static String getMeidFromReflection() {
//        Logger.d(TAG, "call reflection method");
        try {
            Class<?> clazz = Class.forName("android.os.SystemProperties");
            Method method = clazz.getMethod("get", String.class, String.class);
            return (String) method.invoke(null, "ril.cdma.meid", "");
        } catch (Exception e) {
//            Logger.d(TAG, "unable to meid from reflection");
            e.printStackTrace(System.err);
        }

        return null;
    }

	/**
	 * Get meta-data value by key
	 * 
	 * @param context
	 * @param metaKey
	 * @return
	 */
	public static String getMetaValue(Context context, String metaKey) {
		if (context == null || metaKey == null) {
			return null;
		}

		Bundle metaData = null;
		String metaValue = "";
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				metaValue = metaData.getString(metaKey);
				if (null == metaValue) {
					metaValue = String.valueOf(metaData.getInt(metaKey));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return metaValue;
	}

	/**
	 * Get app versionName
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			versionName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 返回应用版本号
	 * 对应build.gradle中的versionCode
	 *
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context) {
		String versionCode = "";
		try {
			versionCode = String.valueOf(context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 获取系统版本号
	 *
	 * @return
	 */
	public static String getBuildVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 获取设备厂商
	 *
	 * @return
	 */
	public static String getDeviceManufacturers() {
		return Build.MANUFACTURER;
	}

	/**
	 * 获取设备型号
	 * @return
	 */
	public static String getDeviceModel() {
		return Build.MODEL;
	}

	/**
	 * 获取设备品牌
	 * @return
	 */
	public static String getDeviceBrand() {
		return Build.BRAND;
	}

	/**
	 * 获取设备品牌
	 * 可能有中文，okhttp会报错，需要过滤
	 */
	public static String getBrand () {
		String brand = filterChinese (Build.BRAND);
		return TextUtils.isEmpty(brand) ? "other_brand" : brand;
	}

	/**
	 * 获取应用名称
	 * @return
     */
	public static String getAppName(Context context) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
			String appName =
					(String) packageManager.getApplicationLabel(applicationInfo);
			return appName;
		} catch (NameNotFoundException e) {
			applicationInfo = null;
			packageManager = null;
			return "";
		}
	}

	/**
	 * Returns MAC address of the given interface name.
	 * @param interfaceName eth0, wlan0 or NULL=use first interface
	 * @return  mac address or empty string
	 */
	public static String getMACAddress(String interfaceName) {
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				if (interfaceName != null) {
					if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
				}
				byte[] mac = intf.getHardwareAddress();
				if (mac==null) return "";
				StringBuilder buf = new StringBuilder();
				for (int idx=0; idx<mac.length; idx++)
					buf.append(String.format("%02X:", mac[idx]));
				if (buf.length()>0) buf.deleteCharAt(buf.length()-1);
				return buf.toString();
			}
		} catch (Exception ex) { } // for now eat exceptions
		return "";
	}
//
//	/**
//	 * 获取构造的user-agent
//	 * @return
//	 */
//	public static String getUserAgent () {
//		String userAgent = "";
//		try {
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//				userAgent = WebSettings.getDefaultUserAgent(MiApplication.getInstance());
//			} else {
//				userAgent = System.getProperty("http.agent");
//			}
//		} catch (Exception e) {
//		}
//
//		String afterFilter = filterChinese (userAgent);
//		if (!TextUtils.isEmpty(afterFilter)) {
//			return afterFilter;
//		}
//
//		StringBuilder sb = new StringBuilder();
//		String versionName = getVersionName (MiApplication.getInstance());
//		if (!TextUtils.isEmpty(versionName)) {
//			sb.append("/").append(versionName);
//		}
//		sb.append(" (Linux; Android ").append(Build.VERSION.RELEASE).append(";");
//		sb.append(" ").append(Build.MANUFACTURER);
//		sb.append(" Build/").append(Build.ID);
//		return filterChinese (sb.toString());
//	}

	// 过滤中文
	public static String filterChinese (String text){
		if (TextUtils.isEmpty(text)) {
			return "";
		}
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0, length = text.length(); i < length; i++) {
				char c = text.charAt(i);
				if (!(c <= '\u001f' || c >= '\u007f')) {
					sb.append(c);
				}
			}

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 获取WiFi地址
	 * @param context
	 * @return
	 */
	public static String getWIFIAddr(Context context){
//		Logger.i(TAG,"获取当前wifi地址");
		String wifiIP = "";
		WifiManager wifi = (WifiManager) context.getApplicationContext()
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiinfo = wifi.getConnectionInfo();
		int intaddr = wifiinfo.getIpAddress();
		byte[] byteaddr = new byte[] { (byte) (intaddr & 0xff),
				(byte) (intaddr >> 8 & 0xff), (byte) (intaddr >> 16 & 0xff),
				(byte) (intaddr >> 24 & 0xff) };
		InetAddress addr = null;
		try {
			addr = InetAddress.getByAddress(byteaddr);
		} catch (Exception e1) {
			e1.printStackTrace();
			return wifiIP;
		}
		wifiIP = addr.getHostAddress();
//		Logger.i(TAG,"当前wifi地址为："+wifiIP);
		return wifiIP;
	}

	public static String getWifiName(Context context){
		String wifiName = "unknown";
		try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                String ssid = networkInfo.getExtraInfo().replace("\"", "");//去引号
                wifiName = filterChinese(ssid);
            }else {
                final WifiManager wifiManager1 = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager1.getConnectionInfo();
                String ssid = wifiInfo.getSSID().replace("\"", "");//去引号
                wifiName = filterChinese(ssid);
            }
//			Logger.i(TAG, "获取当前WiFi名称为 " + wifiName);
		} catch (Exception e){
            e.printStackTrace();
		}
		return wifiName;
	}

	/**
	 * 获取路由地址
	 * @param context
	 * @return
	 */
    public static String getRouterAddr(Context context) {
//        Logger.i(TAG,"获取当前路由地址");
        String connectedWifiMacAddress = "";
        try {
			WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			List<ScanResult> wifiList;

			if (wifiManager != null) {
				wifiList = wifiManager.getScanResults();
				WifiInfo info = wifiManager.getConnectionInfo();
				if (wifiList != null && info != null) {
					for (int i = 0; i < wifiList.size(); i++) {
						ScanResult result = wifiList.get(i);
						if (info.getBSSID().equals(result.BSSID)) {
							connectedWifiMacAddress = result.BSSID;
						}
					}
				}
			}
		} catch (Exception e) {
		}
        return filterChinese(connectedWifiMacAddress);
    }

	/**
	 * 是否存在su命令，并且有执行权限
	 *
	 * @return 存在su命令，并且有执行权限返回1
	 */
	public static int isRootEnable() {
		String[] paths = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"};
		try {
			for (String path : paths) {
				String pathStr = path + "su";
				File file = new File(pathStr);
				if (file.exists() && isCanExecute(pathStr)) {
//					Logger.i(TAG, "find su in : " + path);
					return 1;
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return 0;
	}

	private static boolean isCanExecute(String filePath) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ls -l " + filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String str = in.readLine();
			if (str != null && str.length() >= 4) {
				char flag = str.charAt(3);
				if (flag == 's' || flag == 'x') return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return false;
	}


	public interface OaidCallback {

		void onReceiveOaid(String oaid);
	}

	/**
	 * 获取手机序列号
	 *
	 * @return 手机序列号
	 */
	public static String getSerialNumber(Context context) {
		String serial = "yiduinotfind";
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {//9.0+
				serial = "yiduinotfind";
			} else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
				serial = Build.SERIAL;
			} else {//8.0-
				Class<?> c = Class.forName("android.os.SystemProperties");
				Method get = c.getMethod("get", String.class);
				serial = (String) get.invoke(c, "ro.serialno");
			}
		} catch (Exception e) {
			serial = "yiduinotfind";
			e.printStackTrace();
		}
		return serial;
	}

	/**
	 * 获取当前Wifi或移动网络的Mac地址
	 *
	 * @return	IP地址
	 */
//	public static String getIP(Context context) {
//		ConnectivityManager conMann = (ConnectivityManager)
//				context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		try {
//			NetworkInfo mobileNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//			NetworkInfo wifiNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//
//			if (mobileNetworkInfo.isConnected()) {
//				return getLocalIpAddress();
//			} else if (wifiNetworkInfo.isConnected()) {
//				WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//				WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//				int ipAddress = wifiInfo.getIpAddress();
//				String ip = intToIp(ipAddress);
//				if (TextUtils.isEmpty(ip)) {
//				    return "";
//                } else {
//				    return ip;
//                }
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return "";
//	}

//	/**
//	 * 获取本地移动网络ipv4地址
//	 * @return
//	 */
//	private static String getLocalIpAddress() {
//		try {
//			String ipv4;
//			ArrayList<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
//			for (NetworkInterface ni : nilist) {
//				ArrayList<InetAddress> ialist = Collections.list(ni.getInetAddresses());
//				for (InetAddress address : ialist) {
//					if (!address.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = address.getHostAddress())) {
//					    if (TextUtils.isEmpty(ipv4)) {
//					        // null的时候返回空字符串
//					        return "";
//                        } else {
//						    return ipv4;
//                        }
//					}
//				}
//			}
//
//		} catch (SocketException ex) {
////			Logger.e("local ip", ex.toString());
//		}
//		return "";
//	}

	/**
	 * 获取WI-FI ipv4地址
	 * @param ipInt
	 * @return
	 */
	private static String intToIp(int ipInt) {
		StringBuilder sb = new StringBuilder();
		sb.append(ipInt & 0xFF).append(".");
		sb.append((ipInt >> 8) & 0xFF).append(".");
		sb.append((ipInt >> 16) & 0xFF).append(".");
		sb.append((ipInt >> 24) & 0xFF);
		return sb.toString();
	}


	/**
	 * 获取当前手机是否插入sim卡
	 */
	public static boolean hasSim(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
			return true;
		} else {
			return false;
		}
	}

}
