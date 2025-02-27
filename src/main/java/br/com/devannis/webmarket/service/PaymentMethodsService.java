package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.model.dto.PaymentMethodsRequestDTO;
import br.com.devannis.webmarket.model.dto.PaymentMethodsResponseDTO;
import br.com.devannis.webmarket.model.entity.PaymentMethods;
import br.com.devannis.webmarket.model.enums.PaymentMethodType;
import br.com.devannis.webmarket.repository.PaymentMethodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentMethodsService {

    @Autowired
    private PaymentMethodsRepository paymentMethodsRepository;

    @Transactional
    public PaymentMethodsResponseDTO addPaymentMethods(PaymentMethodsRequestDTO paymentMethodsRequestDTO) {
        PaymentMethods paymentMethods = new PaymentMethods();

        paymentMethods.setPaymentMethodName(PaymentMethodType.valueOf(paymentMethodsRequestDTO.paymentMethodName()));
        paymentMethods.setDescription(paymentMethodsRequestDTO.description());

        PaymentMethods savedPaymentMethods = paymentMethodsRepository.save(paymentMethods);
        return new PaymentMethodsResponseDTO(savedPaymentMethods);
    }

    @Transactional
    public void removePaymentMethods(Long paymentMethodId) {
        paymentMethodsRepository.deleteById(paymentMethodId);
    }

    public List<PaymentMethodsResponseDTO> getAllPaymentMethods() {
        return paymentMethodsRepository
                .findAll()
                .stream()
                .map(PaymentMethodsResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PaymentMethodsResponseDTO getPaymentMethodById(Long paymentMethodId) {
        Optional<PaymentMethods> optionalPaymentMethods = paymentMethodsRepository.findById(paymentMethodId);

        return optionalPaymentMethods.map(PaymentMethodsResponseDTO::new).orElse(null);
    }
}
