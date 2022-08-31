package study.springweb.domain;

public class Member {

    private Long id;
    private String name;

    // getter
    public Long getId() {
        return id;
    }

    // setter
    public void setId(Long id) {
        this.id = id;
    }

    // getter
    public String getName() {
        return name;
    }

    // setter
    public void setName(String spring) {
        this.name = spring;             // spring 파라미터를 받아 String name에 저장한다.
    }
}
