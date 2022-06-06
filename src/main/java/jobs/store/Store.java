package jobs.store;

import jobs.model.Candidate;
import jobs.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {

    private static final Store INST = new Store();
    private AtomicInteger POST_ID = new AtomicInteger(4);
    private AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior java job", "works 3 years", LocalDate.now()));
        posts.put(2, new Post(2, "Middle java job", "works 1 year", LocalDate.now()));
        posts.put(3, new Post(3, "Senior java job", "works 10 years", LocalDate.now()));
        candidates.put(1, new Candidate(1, "Junior java", "3 years", LocalDate.now()));
        candidates.put(2, new Candidate(2, "Middle java", "1 year", LocalDate.now()));
        candidates.put(3, new Candidate(3, "Senior java", "10 years", LocalDate.now()));
    }

    public static Store instOf() {
        return INST;
    }

    public void postSave(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Post findByIdPost(int id) {
        return posts.get(id);
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public void candidateSave(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }
}
