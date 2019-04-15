package com.mycompany.myapp.web.rest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/iss")
public class ISSPositionResource {

    @GetMapping
    public ISSPosition retrieveISSPosition() {
        String result = new RestTemplate().getForObject("https://api.wheretheiss.at/v1/satellites/25544", String.class);
        JSONObject jsonObject = new JSONObject(result);
        return new ISSPosition(jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude"));
    }

    class ISSPosition {
        private Double longitude;
        private Double latitude;

        ISSPosition(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public Double getLatitude() {
            return latitude;
        }
    }
}