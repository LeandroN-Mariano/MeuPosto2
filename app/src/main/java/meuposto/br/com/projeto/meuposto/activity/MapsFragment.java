package meuposto.br.com.projeto.meuposto.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private static final String TAG = "PrincipalActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        //Ativa o GPS
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, this);

    }

    @Override
    public void onPause() {
        super.onPause();

        Toast.makeText(getActivity(), "Pausado!", Toast.LENGTH_LONG).show();
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        LatLng serraTalhada = new LatLng(-8.065638,-34.89);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(serraTalhada));
        mMap.addMarker(new MarkerOptions().position(serraTalhada)).setTitle("Meu local");

        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).target(serraTalhada).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));







    }

    @Override
    public void onMapClick(LatLng latLng) {

        Address endereco;


        try {
            endereco =   mostraEndereco(latLng.latitude,latLng.longitude);
            Toast.makeText(getActivity(), "Cidade: " + endereco.getLocality()+" Bairro: "+endereco.getSubLocality(), Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void onLocationChanged(Location location) {


        Toast.makeText(getActivity(), "Posição alterada!", Toast.LENGTH_LONG).show();

        // Coordenada para a localizacao do GPS
        LatLng novaLocalizacao = new LatLng(location.getLatitude(), location.getLongitude());



        mMap.moveCamera(CameraUpdateFactory.newLatLng(novaLocalizacao));

        //Isso faz con que a cada atualizacao adiciona o marcador, vai ficar adicionando varios
        //Pois a localizacao smepre se altera
      MarkerOptions marker = new MarkerOptions();
        marker.position(novaLocalizacao);
        marker.title("Nova localizacao");

        mMap.addMarker(marker);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(getContext(), "Status do provider foi alterado! ", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getActivity(), "Provider habilitado!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {

        Toast.makeText(getActivity(), "Provider desabilitado!", Toast.LENGTH_LONG).show();

    }


    private  Address mostraEndereco(double latitude, double longitude) throws IOException {

        Geocoder geocoder;
        Address address = null;
        List<Address> enAddresses;

        geocoder = new Geocoder(getContext());

        enAddresses =  geocoder.getFromLocation(latitude,longitude, 1);

        if (enAddresses.size()>0){

            address = enAddresses.get(0);
        }

        return  address;
    }
}
