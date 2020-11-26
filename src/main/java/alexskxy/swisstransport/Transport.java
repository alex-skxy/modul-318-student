package alexskxy.swisstransport;

import alexskxy.swisstransport.entity.Connections;
import alexskxy.swisstransport.entity.StationBoardRoot;
import alexskxy.swisstransport.entity.Stations;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Transport implements ITransport {
    private final Gson gson;

    public Transport() {
        this.gson = new Gson();
    }

    @Override
    public Stations getStations(String query) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            query = URLEncoder.encode(query, StandardCharsets.UTF_8);
            var request = new HttpGet("http://transport.opendata.ch/v1/locations?query=" + query);
            request.addHeader("content-type", "application/json");
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                var stations = gson.fromJson(message, Stations.class);
                return stations;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StationBoardRoot getStationBoard(String station, String id) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            station = URLEncoder.encode(station, StandardCharsets.UTF_8);
            var request = new HttpGet("http://transport.opendata.ch/v1/stationboard?station=" + station + "&id=" + id);
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                var stationBoard = gson.fromJson(message, StationBoardRoot.class);
                return stationBoard;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Connections getConnections(String fromStation, String toStation) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            fromStation = URLEncoder.encode(fromStation, StandardCharsets.UTF_8);
            toStation = URLEncoder.encode(toStation, StandardCharsets.UTF_8);
            var request = new HttpGet("http://transport.opendata.ch/v1/connections?from=" + fromStation + "&to=" + toStation);
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                var connections = gson.fromJson(message, Connections.class);
                return connections;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
