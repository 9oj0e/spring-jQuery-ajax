package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;
    @GetMapping("/api/board/{id}/updateForm")
    public ApiUtil<?> updateForm(@PathVariable Integer id, HttpServletResponse response){
        Board board = boardRepository.selectOne(id);
        if (board == null){
            response.setStatus(404);
            return new ApiUtil<>(404, "존재하지 않는 게시글입니다");
        } // ControllerAdvice 에 throw하는게 낫다
        return new ApiUtil<>(board);
    }
    @PutMapping("/api/board/{id}") // PutMapping이니까, 주소창에 /update를 첨가하지 않음.
    public ApiUtil<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO requestDTO){
        boardRepository.updateById(requestDTO ,id);
        return new ApiUtil<>(null);
    }
    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO requestDTO){
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response){
        Board board = boardRepository.selectOne(id);
        if (board == null){
            response.setStatus(404);
            return new ApiUtil<>(404, "존재하지 않는 게시글입니다");
        } // ControllerAdvice 에 throw하는게 낫다
        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }

    @GetMapping("/api/boards")
    public ApiUtil<?> findAll(){
//        response.setStatus(401);
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter 작동 (Object)
    }
}
