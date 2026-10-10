package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand;

import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.CreateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.UpdateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.output.BrandResponse;
import br.com.teksystem.tekveiculos.vehicle.application.brand.service.BrandService;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.input.BrandRequestDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.output.BrandResponseDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.input.BrandUpdateDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.mapper.BrandWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/brands")
public class BrandRestController {
    private final BrandService brandService;
    private final BrandWebMapper brandWebMapper;

    public BrandRestController(BrandService brandService, BrandWebMapper brandWebMapper) {
        this.brandService = brandService;
        this.brandWebMapper = brandWebMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandResponseDTO save(@Valid @RequestBody BrandRequestDTO brandRequestDto) {
        CreateBrandCommand brandCommand = brandWebMapper.toCreateBrandCommand(brandRequestDto);
        BrandResponse brandResponse = brandService.save(brandCommand);

        return brandWebMapper.toBrandResponseDTO(brandResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponseDTO> findAll() {
        List<BrandResponse> brandResponses = brandService.findAll();

        return brandWebMapper.toBrandResponseDTO(brandResponses);
    }

    @GetMapping("/by-ids")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponseDTO> findAllById(@RequestParam Set<Long> ids) {
        List<BrandResponse> brandResponses = brandService.findAllById(ids);

        return brandWebMapper.toBrandResponseDTO(brandResponses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BrandResponseDTO findById(@PathVariable Long id) {
        return brandWebMapper.toBrandResponseDTO(brandService.findById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BrandResponseDTO update(@PathVariable Long id, @Valid @RequestBody BrandUpdateDTO brandUpdateDto) {
        UpdateBrandCommand updateBrandCommand = brandWebMapper.toUpdateBrandCommand(id, brandUpdateDto);
        BrandResponse brandResponse = brandService.update(id, updateBrandCommand);

        return brandWebMapper.toBrandResponseDTO(brandResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        brandService.deleteById(id);
    }
}
