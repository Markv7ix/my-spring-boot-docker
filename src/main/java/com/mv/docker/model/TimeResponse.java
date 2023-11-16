package com.mv.docker.model;


public class TimeResponse {

  private String time;
  
  private String timezone;

  private TimeResponse(TimeResponseBuilder trb) {
    this.time = trb.time;
    this.timezone = trb.timezone;
  }

  public static TimeResponseBuilder builder() {
    return new TimeResponseBuilder();
  }
  
  public String getTime() {
    return time;
  }

  public String getTimezone() {
    return timezone;
  }
  
  public static class TimeResponseBuilder {
    
    private String time;
    
    private String timezone;
    
    public TimeResponseBuilder withTime(String time) {
      this.time = time;
      return this;
    }

    public TimeResponseBuilder withTimezone(String timezone) {
      this.timezone = timezone;
      return this;
    }
    
    public TimeResponse build() {
      return new TimeResponse(this);
    }
  }
}
