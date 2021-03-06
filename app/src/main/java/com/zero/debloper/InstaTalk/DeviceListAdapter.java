package com.zero.debloper.InstaTalk;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by debloper on 13/9/15.
 *
 * Array adapter for ListFragment that maintains WiFiP2PDevice List
 */
public class DeviceListAdapter extends ArrayAdapter<WifiP2pDevice>{

    private List<WifiP2pDevice> items;

    public DeviceListAdapter(Context context, int resource, List<WifiP2pDevice> objects) {
        super(context, resource, objects);
        items = objects;
    }

    public static String getDeviceStatus(int deviceStatus) {
        Log.d(MainActivity.TAG, "Peer status :" + deviceStatus);
        switch (deviceStatus) {
            case WifiP2pDevice.AVAILABLE:
                return "Available";
            case WifiP2pDevice.INVITED:
                return "Invited";
            case WifiP2pDevice.CONNECTED:
                return "Connected";
            case WifiP2pDevice.FAILED:
                return "Failed";
            case WifiP2pDevice.UNAVAILABLE:
                return "Unavailable";
            default:
                return "Unknown";

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_devices, null);
        }

        WifiP2pDevice device = items.get(position);

        if (device != null) {
            TextView top = (TextView) v.findViewById(R.id.device_name);
            TextView bottom = (TextView) v.findViewById(R.id.device_details);

            if (top != null) {
                top.setText(device.deviceName);
            }

            if (bottom != null) {
                bottom.setText(getDeviceStatus(device.status));
            }
        }

        return v;
        //return super.getView(position, convertView, parent);
    }
}
