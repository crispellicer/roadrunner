package com.svalero.roadrunner.view.races;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import com.svalero.roadrunner.contract.races.RaceDetailsContract;
import com.svalero.roadrunner.domain.Race;

public class RaceDetailsView extends AppCompatActivity implements RaceDetailsContract.View {

    private Race race;
    private RaceDetailsPresenter presenter;
    private long raceId;

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_details_view);

        mapView = findViewById(R.id.raceMap);
        initializePointManager();

        presenter = new RaceDetailsPresenter(this);
        Intent intent = getIntent();
        raceId = getIntent().getLongExtra("raceId", 0);

        presenter.loadDetailsRace(raceId);

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    private void addRaceToMap(Race race) {
        Point point = Point.fromLngLat(race.getLongitude(), race.getLatitude());
        addMarker(point, race.getName());

        setCameraPosition(Point.fromLngLat(race.getLongitude(), race.getLatitude()));

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
    public void showRace(Race race) {
        TextView tvRaceName = findViewById(R.id.tv_race_name_details);
        TextView tvRaceDistance = findViewById(R.id.tv_race_distance_details);
        TextView tvRaceType = findViewById(R.id.tv_race_type_details);
        TextView tvRaceCity = findViewById(R.id.tv_race_city_details);
        TextView tvRaceRegistrationPrice = findViewById(R.id.tv_race_registration_price_details);
        TextView tvRaceDate = findViewById(R.id.tv_race_date_details);

        tvRaceName.setText(race.getName());
        tvRaceDistance.setText(race.getDistance());
        tvRaceType.setText(race.getType());
        tvRaceCity.setText(race.getCity());
        tvRaceRegistrationPrice.setText(String.valueOf(race.getRegistrationPrice()));
        tvRaceDate.setText(race.getRaceDate());

        addRaceToMap(race);
    }

    @Override
    public void showMessage(String message) {

    }
}
