package com.mv.docker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mv.docker.model.TimeRequest;
import com.mv.docker.model.TimeResponse;

@RestController
public class HelloController {

  private static Logger logger = LoggerFactory.getLogger(HelloController.class);

  @RequestMapping("/")
  public String index() {
    return "Hello from Spring Boot!";
  }

  @GetMapping("/api/tz")
  public ResponseEntity<ArrayList<String>> getTimezones() {
    logger.info("Get Zone Id list invoked");
    ArrayList<String> timezones = new ArrayList<String>(ZoneId.getAvailableZoneIds());
    return ResponseEntity.ok(timezones);
  }

  @GetMapping(value = "/api/getTime", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TimeResponse> getDefaultTime() {
    logger.info("Get default Time invoked");
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime dt = getLocalDateTime(zoneId);
    TimeResponse tr =
        TimeResponse.builder().withTime(dt.toString()).withTimezone(zoneId.toString()).build();
    return ResponseEntity.ok(tr);
  }

  @PostMapping(value = "/api/getTime", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TimeResponse> getTimeByZoneId(@RequestBody TimeRequest request) {
    logger.info("Get Time by Zone ID invoked: {}", request.getZoneId());
    ZoneId zoneId = ZoneId.of(request.getZoneId());
    LocalDateTime dt = getLocalDateTime(zoneId);
    TimeResponse tr =
        TimeResponse.builder().withTime(dt.toString()).withTimezone(request.getZoneId()).build();
    return ResponseEntity.ok(tr);
  }

  private LocalDateTime getLocalDateTime(ZoneId zoneId) {
    return LocalDateTime.now(zoneId);
  }
}
