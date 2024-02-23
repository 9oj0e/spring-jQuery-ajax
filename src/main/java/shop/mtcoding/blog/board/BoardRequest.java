package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {
    @Data
    public static class WriteDTO{
        private String title;
        private String content;
        private String author;

    }
    @Data
    public static class UpdateDTO{
        // DTO의 내용이 동일하더라도, 사용되는 메서드(상황) 다르면 중복정의 하는것이 편하다.
        private String title;
        private String content;
        private String author;

    }
}
