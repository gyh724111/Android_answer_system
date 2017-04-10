package cn.yonghuige.testjson.model;

/**
 * Created by gyh on 2017/3/27.
 */

public class MOs {
    String teacher_name;
    String these_course;
    String others;
    int order_answer_id;

    public MOs(String these_course, String teacher_name, String others,int order_answer_id) {
        setThese_course(these_course);
        setTeacher_name(teacher_name);
        setOthers(others);
        setOrder_answer_id(order_answer_id);
    }

    public int getOrder_answer_id() {
        return order_answer_id;
    }

    public void setOrder_answer_id(int order_answer_id) {
        this.order_answer_id = order_answer_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getThese_course() {
        return these_course;
    }

    public void setThese_course(String these_course) {
        this.these_course = these_course;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
