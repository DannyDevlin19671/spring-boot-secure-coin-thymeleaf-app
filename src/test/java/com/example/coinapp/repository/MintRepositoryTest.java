package com.example.coinapp.repository;

import com.example.coinapp.model.Mint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class MintRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(MintRepositoryTest.class);

    @Autowired
    private MintRepository mintRepository;

    @BeforeEach
    void setup() {
        mintRepository.deleteAll();
        mintRepository.save(new Mint("United Kingdom"));
        mintRepository.save(new Mint("France"));
        mintRepository.save(new Mint("Germany"));
    }

    @Test
    void shouldFindByCountry() {
        logger.info("🔍 Testing findByCountry for 'United Kingdom'");
        Optional<Mint> result = mintRepository.findByCountry("United Kingdom");
        assertThat(result).isPresent();
        assertThat(result.get().getCountry()).isEqualTo("United Kingdom");
        logger.info("✅ Passed findByCountry test");
    }

    @Test
    void shouldFindByCountryIgnoreCase() {
        logger.info("🔍 Testing findByCountryIgnoreCase for 'united kingdom'");
        List<Mint> results = mintRepository.findByCountryIgnoreCase("united kingdom");
        assertThat(results).hasSize(1);
        logger.info("✅ Passed findByCountryIgnoreCase test");
    }

    @Test
    void shouldFindByCountryContainingCaseSensitive() {
        logger.info("🔍 Testing findByCountryContaining for 'United'");
        List<Mint> results = mintRepository.findByCountryContaining("United");
        assertThat(results).hasSize(1);
        logger.info("✅ Passed findByCountryContaining test");
    }

    @Test
    void shouldCheckExistenceByCountry() {
        logger.info("🔍 Testing existsByCountry for 'France'");
        boolean exists = mintRepository.existsByCountry("France");
        assertThat(exists).isTrue();
        logger.info("✅ Passed existsByCountry test");
    }

    @Test
    void shouldCountByCountry() {
        logger.info("🔍 Testing countByCountry for 'Germany'");
        long count = mintRepository.countByCountry("Germany");
        assertThat(count).isEqualTo(1L);
        logger.info("✅ Passed countByCountry test");
    }

    @Test
    void shouldFindFirstAlphabetically() {
        logger.info("🔍 Testing findFirstByOrderByCountryAsc");
        Optional<Mint> first = mintRepository.findFirstByOrderByCountryAsc();
        assertThat(first).isPresent();
        assertThat(first.get().getCountry()).isEqualTo("France");
        logger.info("✅ Passed findFirstByOrderByCountryAsc test");
    }
}