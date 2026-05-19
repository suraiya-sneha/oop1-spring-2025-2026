package entity;

public class Member {

    private String id;
    private String name;
    private String age;
    private String plan;

    public Member(String id, String name, String age, String plan) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.plan = plan;
    }

    public String getId(){ return id; }
    public String getName(){ return name; }
    public String getAge(){ return age; }
    public String getPlan(){ return plan; }

    public void setId(String id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setAge(String age){ this.age = age; }
    public void setPlan(String plan){ this.plan = plan; }

    public String toLine(){
        return id + "," + name + "," + age + "," + plan;
    }

    public static Member fromLine(String line){
        if(line == null || line.isEmpty()) return null;

        String[] data = line.split(",", -1);
        if(data.length != 4) return null;

        return new Member(data[0], data[1], data[2], data[3]);
    }

    public Object[] toRow(){
        return new Object[]{id, name, age, plan};
    }
}