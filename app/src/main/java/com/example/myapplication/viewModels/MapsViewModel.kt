import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.base.BuildConfig
import com.app.base.base_classes.BaseFragment
import com.google.android.gms.maps.GoogleMap
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


class MapsViewModel : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.app.base.R.layout.fragment_location, container, false)
        val ctx = activity!!.applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        val map = view.findViewById<MapView>(com.example.base.R.id.mapview)
        map.setUseDataConnection(true)
//val map = view.findViewById(R.id.map) as MapView
        map.setTileSource(TileSourceFactory.MAPNIK)
//map.setBuiltInZoomControls(true) //Map ZoomIn/ZoomOut Button Visibility
        map.setMultiTouchControls(true)
        val mapController: IMapController
        mapController = map.getController()
//mapController.zoomTo(14, 1)
        mapController.setZoom(14)
        val mGpsMyLocationProvider = GpsMyLocationProvider(activity)
        val mLocationOverlay = MyLocationNewOverlay(mGpsMyLocationProvider, map)
        mLocationOverlay.enableMyLocation()
        mLocationOverlay.enableFollowLocation()
        val icon = BitmapFactory.decodeResource(resources, com.app.base.R.drawable.ic_menu_compass)
        mLocationOverlay.setPersonIcon(icon)
        map.getOverlays().add(mLocationOverlay)
        mLocationOverlay.runOnFirstFix {
            map.getOverlays().clear()
            map.getOverlays().add(mLocationOverlay)
            mapController.animateTo(mLocationOverlay.myLocation)
        }
        return view
    }
}