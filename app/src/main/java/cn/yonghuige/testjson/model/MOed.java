package cn.yonghuige.testjson.model;

/**
 * Created by gyh on 2017/3/27.
 */

public class MOed {
    String My_Ordered_id;
    String wait_answer_id;
    String these_course;
    String answer_time;
    String answer_position;
    String others;

    public String getMy_Ordered_id() {
        return My_Ordered_id;
    }

    public void setMy_Ordered_id(String my_Ordered_id) {
        My_Ordered_id = my_Ordered_id;
    }

    public String getWait_answer_id() {
        return wait_answer_id;
    }

    public void setWait_answer_id(String wait_answer_id) {
        this.wait_answer_id = wait_answer_id;
    }

    public String getThese_course() {
        return these_course;
    }

    public void setThese_course(String these_course) {
        this.these_course = these_course;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    public String getAnswer_position() {
        return answer_position;
    }

    public void setAnswer_position(String answer_position) {
        this.answer_position = answer_position;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count;

    public MOed(String My_Ordered_id, String wait_answer_id, String these_course, String answer_time,
                String answer_position, String others, int count) {
        setMy_Ordered_id(My_Ordered_id);
        setWait_answer_id(wait_answer_id);
        setThese_course(these_course);
        setAnswer_time(answer_time);
        setAnswer_position(answer_position);
        setOthers(others);
        setCount(count);
    }

}
