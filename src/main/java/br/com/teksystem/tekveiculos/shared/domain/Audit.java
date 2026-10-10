package br.com.teksystem.tekveiculos.shared.domain;

import java.time.Instant;
import java.util.Objects;

public record Audit(Instant createdAt, String createdBy, Instant modifiedAt, String modifiedBy) {
    public Audit {
        Objects.requireNonNull(createdAt, "Date of create is mandatory!");
        Objects.requireNonNull(createdBy, "Author of create is mandatory!");
    }

    public static Audit create(String author) {
        Instant now = Instant.now();
        return new Audit(now, author, now, author);
    }

    public Audit update(String author) {
        return new Audit(this.createdAt, this.createdBy, Instant.now(), author);
    }
}
