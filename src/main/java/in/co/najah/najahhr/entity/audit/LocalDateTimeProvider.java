package in.co.najah.najahhr.entity.audit;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;


public class LocalDateTimeProvider implements DateTimeProvider {

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now(Clock.systemUTC()).atOffset(ZoneOffset.UTC));
    }
}