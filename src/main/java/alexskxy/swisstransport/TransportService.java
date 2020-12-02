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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransportService implements ITransport {
    private final Gson gson;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public TransportService() {
        this.gson = new Gson();
    }

    @Override
    public Stations getStations(String query) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            query = urlEncode(query);
            var request = new HttpGet("http://transport.opendata.ch/v1/locations?query=" + query);
            request.addHeader("content-type", "application/json");
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                return gson.fromJson(message, Stations.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StationBoardRoot getStationBoard(String station) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            station = urlEncode(station);
            var request = new HttpGet("http://transport.opendata.ch/v1/stationboard?station=" + station);
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                return gson.fromJson(message, StationBoardRoot.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Connections getConnections(String fromStation, String toStation) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            fromStation = urlEncode(fromStation);
            toStation = urlEncode(toStation);
            var request = new HttpGet("http://transport.opendata.ch/v1/connections?from=" + fromStation + "&to=" + toStation);
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                return gson.fromJson(message, Connections.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connections getConnections(String fromStation, String toStation, LocalDate date, LocalTime time, boolean isArrival) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            var request = new HttpGet("http://transport.opendata.ch/v1/connections?from="
                    + urlEncode(fromStation)
                    + "&to=" + urlEncode(toStation)
                    + "&date=" + urlEncode(dateFormatter.format(date))
                    + "&time=" + urlEncode(timeFormatter.format(time))
                    + "&isArrivalTime=" + urlEncode(String.valueOf(isArrival ? 1 : 0)));
            var response = httpClient.execute(request);

            if (response != null) {
                var message = EntityUtils.toString(response.getEntity(), "UTF-8");
                return gson.fromJson(message, Connections.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String urlEncode(String date) {
        return URLEncoder.encode(date, StandardCharsets.UTF_8);
    }
}
