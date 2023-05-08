package com.cnu.simple.work;

import com.cnu.simple.ApiResponse;
import com.cnu.simple.exception.WorkSpecificationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class WorkSpecificationController {

    @Autowired
    private WorkSpecificationService workService;

    @ExceptionHandler(WorkSpecificationNotFoundException.class)
    public ApiResponse<String> workSpecNotFoundExceptionHandle(WorkSpecificationNotFoundException exception){
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @PostMapping("")
    public ApiResponse<WorkSpecResponseDto> addWorkSpec(@RequestBody WorkSpecRequestDto workSpecRequestDto){
        return ApiResponse.ok(
                workService.addWorkSpec(workSpecRequestDto)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<WorkSpecResponseDto> getWorkSpec(@PathVariable("id") Long id){
        return ApiResponse.ok(
                workService.getWorkSpec(id)
        );
    }

    @GetMapping("/all")
    public ApiResponse<List<WorkSpecResponseDto>> getAllWrokSpec(){
        return ApiResponse.ok(
                workService.getAllWorkSpec()
        );
    }
    @GetMapping("/all/{memberId}")
    public ApiResponse<List<WorkSpecResponseDto>> getWorkSpecByMemberId(@PathVariable("memberId") UUID memberId){
        return ApiResponse.ok(
                workService.getWorkSpecByMemberId(memberId)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<WorkSpecResponseDto> modifyWorkSpec(@PathVariable("id") Long id,
                                                           @RequestBody WorkSpecRequestDto workSpecRequestDto){
        return ApiResponse.ok(
                workService.modifyWorkSpec(id, workSpecRequestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> removeWorkSpec(@PathVariable("id") Long id){
        workService.removeWorkSpec(id);
        return ApiResponse.ok("Delete WorkSpecification Success : id " + id);
    }
}
