package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public interface IPackageManager {

  ParceledListSlice<?> getAllPermissionGroups(int flags) throws RemoteException;

  ParceledListSlice<?> queryPermissionsByGroup(String group, int flags) throws RemoteException;

  int getPermissionFlags(String permName, String pkgName, int userId) throws RemoteException;

  void grantRuntimePermission(String pkgName, String permName, int userId) throws RemoteException;

  void revokeRuntimePermission(String pkgName, String permName, int userId) throws RemoteException;

  void setApplicationEnabledSetting(String pkg, int state, int flags, int userId, String callingPkg)
      throws RemoteException;

  String[] getPackagesForUid(int uid) throws RemoteException;

  abstract class Stub {

    @SuppressWarnings("UnusedDeclaration")
    public static IPackageManager asInterface(IBinder obj) {
      return null;
    }
  }
}
