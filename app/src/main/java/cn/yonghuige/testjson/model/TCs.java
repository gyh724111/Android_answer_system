package cn.yonghuige.testjson.model;

/**
 * Created by gyh on 2017/3/6.
 */

public class TCs {
    private String id;
    private String teacher_name;
    private String these_courses;
    private String answer_time;
    private String others;
    private String order_count;

    public TCs(String id, String teacher_name, String these_courses, String answer_time, String others) {
        setId(id);
        setTeacher_name(teacher_name);
        setThese_courses(these_courses);
        setAnswer_time(answer_time);
        setOthers(others);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTeacher_name() {
        return teacher_name;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;

    }

    public String getThese_courses() {
        return these_courses;
    }

    public void setThese_courses(String these_courses) {
        this.these_courses = these_courses;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
