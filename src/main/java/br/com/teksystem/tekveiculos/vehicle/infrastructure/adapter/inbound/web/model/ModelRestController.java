package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model;

import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.CreateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.UpdateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.output.ModelResponse;
import br.com.teksystem.tekveiculos.vehicle.application.model.service.ModelService;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.input.ModelRequestDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.output.ModelResponseDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.input.ModelUpdateDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.mapper.ModelWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelRestController {
    public final ModelService modelService;
    public final ModelWebMapper modelWebMapper;

    public ModelRestController(ModelService modelService, ModelWebMapper modelWebMapper) {
        this.modelService = modelService;
        this.modelWebMapper = modelWebMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelResponseDTO save(@Valid @RequestBody ModelRequestDTO modelRequestDto) {
        CreateModelCommand createModelCommand = modelWebMapper.toCreateModelCommand(modelRequestDto);
        ModelResponse modelResponse = modelService.save(createModelCommand);

        return modelWebMapper.toModelResponseDTO(modelResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ModelResponseDTO> findAll() {
        List <ModelResponse> modelResponseList = modelService.findAll();

        return modelWebMapper.toListModelResponseDTO(modelResponseList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelResponseDTO findById(@PathVariable Long id) {
        return modelWebMapper.toModelResponseDTO(modelService.findById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelResponseDTO update(@PathVariable Long id, @Valid @RequestBody ModelUpdateDTO modelUpdateDto) {
        UpdateModelCommand updateModelCommand = modelWebMapper.toUpdateModelCommand(id, modelUpdateDto);
        ModelResponse modelResponse = modelService.update(id, updateModelCommand);

        return modelWebMapper.toModelResponseDTO(modelResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        modelService.deleteById(id);
    }
}
