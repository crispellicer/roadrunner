package com.svalero.roadrunner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.contract.races.RacesListContract;
import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.presenter.races.RacesListPresenter;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements RacesListContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    private RacesListPresenter racesListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);
        initializePointManager();

        racesListPresenter = new RacesListPresenter(this);
        racesListPresenter.loadAllRaces();
    }

    private void addRacesToMap(List<Race> races) {
        for (Race race : races) {
            Point point = Point.fromLngLat(race.getLongitude(), race.getLatitude());
            addMarker(point, race.getName());
        }

        Race lastRace = races.get(races.size() - 1);
        setCameraPosition(Point.fromLngLat(lastRace.getLongitude(), lastRace.getLatitude()));
    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    private void addMarker(Point point, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

    @Override
    public void showRaces(List<Race> races) {
        addRacesToMap(races);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
