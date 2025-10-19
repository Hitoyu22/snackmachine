package org.example.snackmachine.infra.controller;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Repository
public class OrderProcessor {

    private Instant startedAt;
    private boolean failed = false;

    void start() {
        startedAt = Instant.now();
    }

    Status status() {
        var now = Instant.now();

        if (failed) {
            startedAt = null;
            failed = false;
            return Status.Failed;
        }

        if (startedAt == null) {
            return Status.Nothing;
        }

        if (startedAt.until(now, ChronoUnit.SECONDS) > 5) {
            startedAt = null;
            return Status.Done;
        }

        return Status.Pending;
    }

    public void fails() {
        failed = true;
    }
}
