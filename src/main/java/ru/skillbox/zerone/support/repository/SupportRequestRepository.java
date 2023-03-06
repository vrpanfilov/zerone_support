package ru.skillbox.zerone.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.zerone.support.model.entity.SupportRequest;
import ru.skillbox.zerone.support.model.enumerated.SupportRequestStatus;

import java.util.List;

public interface SupportRequestRepository extends JpaRepository<SupportRequest, Long> {
  List<SupportRequest> findAllByStatusOrderById(SupportRequestStatus status);
}
