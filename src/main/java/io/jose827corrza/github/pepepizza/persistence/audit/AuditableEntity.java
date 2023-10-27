package io.jose827corrza.github.pepepizza.persistence.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity {
    // For auditing
    @Column(name = "created_at")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;

    @Column(name = "updated_at")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedDate;
}
