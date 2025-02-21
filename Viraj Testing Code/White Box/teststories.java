package jobscheduling;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;


// Test class for teststories
public class teststories {

    @Test
    public void testAddJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("ADM-100", "TaskA", Arrays.asList("urgent", "review"));
        scheduler.addJob(job);

        // Verify job count and details
        assertEquals(1, scheduler.Jobs().size());
        assertEquals("TaskA", scheduler.Jobs().get(0).getJobName());
        assertEquals("ADM-100", scheduler.Jobs().get(0).getJobId());
    }

    @Test
    public void testFinalizeJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("ADM-100", "TaskA", Arrays.asList("urgent"));
        scheduler.addJob(job);

        scheduler.finalizeJob("ADM-100");

        // Verify job is finalized
        assertTrue(job.isFinalized());
    }

    @Test
    public void testAssignTags() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("ADM-100", "TaskA", Arrays.asList("urgent"));
        scheduler.addJob(job);

        scheduler.assignTags("ADM-100", Arrays.asList("priority", "client"));

        // Verify tags are updated
        assertEquals(Arrays.asList("priority", "client"), job.getTags());

        // Finalize job and attempt to modify tags
        scheduler.finalizeJob("ADM-100");
        scheduler.assignTags("ADM-100", Arrays.asList("test"));
        assertEquals(Arrays.asList("priority", "client"), job.getTags()); // Tags remain unchanged
    }

    @Test
    public void testGenerateJobId() {
        JobScheduler scheduler = new JobScheduler();

        // Generate IDs for different roles
        String id1 = scheduler.generateJobId("Admin");
        String id2 = scheduler.generateJobId("Manager");
        String id3 = scheduler.generateJobId("Developer");
        String id4 = scheduler.generateJobId("User");

        // Verify IDs have the correct prefix
        assertTrue(id1.startsWith("ADM-"));
        assertTrue(id2.startsWith("MGR-"));
        assertTrue(id3.startsWith("DEV-"));
        assertTrue(id4.startsWith("USR-"));
    }

    @Test
    public void testFilterJobsByTags() {
        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("ADM-100", "TaskA", Arrays.asList("urgent"));
        Job job2 = new Job("MGR-101", "TaskB", Arrays.asList("review"));
        scheduler.addJob(job1);
        scheduler.addJob(job2);

        // Use the filter method and verify manually via console
        scheduler.filterJobsByTags("urgent");

        // No automated assertion; check the console output
    }
}

