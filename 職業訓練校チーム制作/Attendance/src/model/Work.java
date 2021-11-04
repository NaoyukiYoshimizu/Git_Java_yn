package model;

//勤怠表

import java.io.Serializable;

public class Work implements Serializable {

	private String work_day; // 日付(String)
	private String stamping_time; // 打刻時間
	private String note; // 備考
	private int id; // 社員番号
	private int time_id; // 時間区分ID
	private String workout_stamping_time; // 退勤時間
	private int workout_time_id; // 退勤id
	private String inoutflag; // 出勤・退勤を判断するフラグ（追加分）


	public Work() {
	}

	public Work(String work_day, String stamping_time, String note, int id, int time_id, String workout_stamping_time,
			int workout_time_id) {
		this.work_day = work_day;
		this.stamping_time = stamping_time;
		this.note = note;
		this.id = id;
		this.time_id = time_id;
		this.workout_stamping_time = workout_stamping_time;
		this.workout_time_id = workout_time_id;
	}

	public String getWorkout_stamping_time() {
		return workout_stamping_time;
	}

	public void setWorkout_stamping_time(String workout_stamping_time) {
		this.workout_stamping_time = workout_stamping_time;
	}

	public int getWorkout_time_id() {
		return workout_time_id;
	}

	public void setWorkout_time_id(int workout_time_id) {
		this.workout_time_id = workout_time_id;
	}

	public Work(int user_id) {
		this.id = user_id;
	}

	public String getWork_day() {
		return work_day;
	}

	public void setWork_day(String work_day) {
		this.work_day = work_day;
	}

	public String getStamping_time() {
		return stamping_time;
	}

	public void setStamping_time(String stamping_time) {
		this.stamping_time = stamping_time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTime_id() {
		return time_id;
	}

	public void setTime_id(int time_id) {
		this.time_id = time_id;
	}

	public String getInoutflag() {
		return inoutflag;
	}

	public void setInoutflag(String inoutflag) {
		this.inoutflag = inoutflag;
	}

}
