package main;

import java.util.ArrayList;

public class ADB {

    private String ID;

    public ADB(String deviceID) {
        ID = deviceID;
    }

    public static String command(String command) {
        if (command.startsWith("adb"))
            command = command.replace("adb ", ServerAgent.getAndroidHome() + "/platform-tools/adb ");
        else
            throw new RuntimeException("This method is for ADB command only.");
        String output = ServerAgent.runCommand(command);
        if (output == null)
            return "";
        else
            return output.trim();
    }

    public static void startServer() {
        command("adb start-server");
    }

    public static void killServer() {
        command("adb kill-server");
    }

    public static ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device"))
                devices.add(line.replace("device", "").trim());
        }
        return devices;
    }

    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.realease");
        if (output.length() == 3)
            output += ".0";
        return output;
    }

    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public String getDeviceModel() {
        return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    public String getDeviceSerialNumber() {
        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    public ArrayList getInstalledPackages() {
        ArrayList packages = new ArrayList();
        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String packageID : output)
            packages.add(packageID.replace("package:", "").trim());
        return packages;
    }

}
