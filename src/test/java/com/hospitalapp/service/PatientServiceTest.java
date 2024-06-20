package com.hospitalapp.service;

import java.time.Instant;
import java.util.*;

import com.hospitalapp.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.hospitalapp.AbstractTest;
import com.hospitalapp.BasicTransactionalTest;
import com.hospitalapp.model.Patient;

/**
 * Unit test methods for the patientService and patientServiceBean.

 */
@RunWith(SpringRunner.class)
@BasicTransactionalTest
public class PatientServiceTest extends AbstractTest {

    /**
     * Constant 'test'.
     */
    private static final String VALUE_TEXT = "test";

    /**
     * The patientService business service.
     */
    @Autowired
    private transient PatientService patientService;
    /**
     * The departmentService business service.
     */
    @Autowired
    private transient DepartmentService departmentService;

    @Override
    public void doBeforeEachTest() {

    }

    @Override
    public void doAfterEachTest() {
        // perform test clean up
    }

    /**
     * Test fetch a collection of Patients.
     */
    @Test
    public void testGetPatients() {

        final Collection<Patient> Patients = patientService.findAll(null);

        Assert.assertNotNull("failure - expected not null", Patients);
        Assert.assertEquals("failure - expected 0 Patients", 0, Patients.size());

    }

    /**
     * Test fetch a single Patient with invalid identifier.
     */
    @Test
    public void testGetPatientNotFound() {

        final Long id = Long.MAX_VALUE;

        final Optional<Patient> patientOptional = patientService.getById(id);

        Assert.assertTrue("failure - expected null", patientOptional.isEmpty());
    }

    /**
     * Test create a Patient.
     */
    @Test
    public void testCreatePatient() {
        final Patient patient = new Patient();
        final Department newDepartment = new Department();
        newDepartment.setCode("code");
        newDepartment.setName("name");

        final Department createdDepartment = departmentService.upsert(newDepartment);
        patient.setDepartment(createdDepartment);
        patient.setName(VALUE_TEXT);
        patient.setLastName(VALUE_TEXT);
        patient.setBirthDate(Instant.now());

        final Patient createdPatient = patientService.upsert(patient);

        Assert.assertNotNull("failure - expected patient not null", createdPatient);
        Assert.assertNotNull("failure - expected patient.id not null", createdPatient.getId());
        Assert.assertEquals("failure - expected patient.name match", VALUE_TEXT, createdPatient.getName());
        Assert.assertEquals("failure - expected patient.lastName match", VALUE_TEXT, createdPatient.getLastName());

        final List<Patient> patients = patientService.findAll(null);

        Assert.assertEquals("failure - expected 1 patients", 1, patients.size());

    }

    /**
     * Test update a Patient which does not exist.
     */
    @Test
    public void testUpdatePatientNotFound() {
        final Department newDepartment = new Department();
        newDepartment.setCode("code");
        newDepartment.setName("name");
        final Department createdDepartment = departmentService.upsert(newDepartment);

        final Patient patient = new Patient();
        patient.setId(1234522L);
        patient.setName(VALUE_TEXT);
        patient.setLastName(VALUE_TEXT);
        patient.setDepartment(createdDepartment);
        patient.setName(VALUE_TEXT);
        patient.setLastName(VALUE_TEXT);
        patient.setBirthDate(Instant.now());

        try {
            patientService.upsert(patient);
            Assert.fail("failure - expected exception");
        } catch (NoSuchElementException ex) {
            final List<Patient> patients = patientService.findAll(null);
            Assert.assertEquals("failure - expected 0 patient", 0, patients.size());
        }
    }

}
