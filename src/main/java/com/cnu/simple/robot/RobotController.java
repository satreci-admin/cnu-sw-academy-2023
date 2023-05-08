package com.cnu.simple.robot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/robot")
@RequiredArgsConstructor
public class RobotController {
    private final RobotService robotService;

    @PostMapping
    public ResponseEntity<RobotResponseDto> createRobot(@RequestBody RobotRequestDto robotRequestDto) {
        RobotResponseDto createdRobot = robotService.createRobot(robotRequestDto);
        return new ResponseEntity<>(createdRobot, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RobotResponseDto>> getRobots() {
        List<RobotResponseDto> robotResponseDtos = robotService.getRobots();
        return ResponseEntity.ok(robotResponseDtos);
    }

    @GetMapping("/{robotId}")
    public ResponseEntity<RobotResponseDto> getRobot(@PathVariable("robotId") Long robotId) {
        Optional<RobotResponseDto> robotResponseDto = robotService.getRobot(robotId);
        if (robotResponseDto.isPresent()) {
            return ResponseEntity.ok(robotResponseDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{robotId}")
    public ResponseEntity<RobotResponseDto> updateRobot(@PathVariable("robotId") Long robotId,
                                                       @RequestBody RobotRequestDto robotRequestDto) {
        Optional<RobotResponseDto> updatedRobotResponse = robotService.updateRobot(robotId, robotRequestDto);
        if (updatedRobotResponse.isPresent()) {
            return ResponseEntity.ok(updatedRobotResponse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{robotId}")
    public ResponseEntity<Void> deleteRobot(@PathVariable("robotId") Long robotId) {
        robotService.deleteRobot(robotId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/test/connection")
    public ResponseEntity<Boolean> connectRobot(@RequestBody RobotRequestDto robotRequestDto) {
        boolean isSuccess = robotService.testSshConnection(robotRequestDto);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
