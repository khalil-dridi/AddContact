import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {

    // Méthode pour vérifier si une autorisation est accordée
    public static boolean checkPermission(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    // Méthode pour demander une autorisation
    public static void requestPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }
    if (!PermissionManager.checkPermission(this, android.Manifest.permission.INTERNET)) {
        PermissionManager.requestPermission(this, android.Manifest.permission.INTERNET, PERMISSION_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // L'autorisation a été accordée, vous pouvez poursuivre.
            } else {
                // L'autorisation a été refusée, traitez ce cas selon les besoins de votre application.
            }
        }
    }

}
