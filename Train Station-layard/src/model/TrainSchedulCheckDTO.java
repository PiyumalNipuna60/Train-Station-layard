package model;

public class TrainSchedulCheckDTO {
    private String From;
    private String To;
    private String TrainId;
    private String TrainName;
    private String StartTrainTime;
    private String EndStationTime;
    private String TrainStopTime;
    private String TrainStartStation;
    private String TrainEndStation;

    public TrainSchedulCheckDTO() {
    }

    public TrainSchedulCheckDTO(String from, String to, String trainId, String trainName, String startTrainTime, String endStationTime, String trainStopTime, String trainStartStation, String trainEndStation) {
        setFrom(from);
        setTo(to);
        setTrainId(trainId);
        setTrainName(trainName);
        setStartTrainTime(startTrainTime);
        setEndStationTime(endStationTime);
        setTrainStopTime(trainStopTime);
        setTrainStartStation(trainStartStation);
        setTrainEndStation(trainEndStation);
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getTrainId() {
        return TrainId;
    }

    public void setTrainId(String trainId) {
        TrainId = trainId;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public String getStartTrainTime() {
        return StartTrainTime;
    }

    public void setStartTrainTime(String startTrainTime) {
        StartTrainTime = startTrainTime;
    }

    public String getEndStationTime() {
        return EndStationTime;
    }

    public void setEndStationTime(String endStationTime) {
        EndStationTime = endStationTime;
    }

    public String getTrainStopTime() {
        return TrainStopTime;
    }

    public void setTrainStopTime(String trainStopTime) {
        TrainStopTime = trainStopTime;
    }

    public String getTrainStartStation() {
        return TrainStartStation;
    }

    public void setTrainStartStation(String trainStartStation) {
        TrainStartStation = trainStartStation;
    }

    public String getTrainEndStation() {
        return TrainEndStation;
    }

    public void setTrainEndStation(String trainEndStation) {
        TrainEndStation = trainEndStation;
    }

    @Override
    public String toString() {
        return "TrainSchedulCheck{" +
                "From='" + From + '\'' +
                ", To='" + To + '\'' +
                ", TrainId='" + TrainId + '\'' +
                ", TrainName='" + TrainName + '\'' +
                ", StartTrainTime='" + StartTrainTime + '\'' +
                ", EndStationTime='" + EndStationTime + '\'' +
                ", TrainStopTime='" + TrainStopTime + '\'' +
                ", TrainStartStation='" + TrainStartStation + '\'' +
                ", TrainEndStation='" + TrainEndStation + '\'' +
                '}';
    }
}
