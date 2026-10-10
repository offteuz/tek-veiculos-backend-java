package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle;

import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.CreateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.UpdateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.service.VehicleService;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.input.VehicleRequestDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.input.VehicleUpdateDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.output.VehicleResponseDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.mapper.VehicleWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleRestController {
    private final VehicleService vehicleService;
    private final VehicleWebMapper vehicleWebMapper;

    public VehicleRestController(VehicleService vehicleService, VehicleWebMapper vehicleWebMapper) {
        this.vehicleService = vehicleService;
        this.vehicleWebMapper = vehicleWebMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDTO save(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        CreateVehicleCommand createVehicleCommand = vehicleWebMapper.toCreateVehicleCommand(vehicleRequestDTO);
        VehicleResponse vehicleResponse = vehicleService.save(createVehicleCommand);

        return vehicleWebMapper.toVehicleResponseDTO(vehicleResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponseDTO> findAll() {
        List<VehicleResponse> vehicleResponseList = vehicleService.findAll();

        return vehicleWebMapper.toListVehicleResponseDTO(vehicleResponseList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponseDTO findById(@PathVariable Long id) {
        return vehicleWebMapper.toVehicleResponseDTO(vehicleService.findById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponseDTO update (@PathVariable Long id, @Valid @RequestBody VehicleUpdateDTO vehicleUpdateDTO) {
        UpdateVehicleCommand updateVehicleCommand = vehicleWebMapper.toUpdateVehicleCommand(vehicleUpdateDTO);
        VehicleResponse vehicleResponse = vehicleService.update(id, updateVehicleCommand);

        return vehicleWebMapper.toVehicleResponseDTO(vehicleResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vehicleService.deleteById(id);
    }
}
