package com.step.jliang.beanDO;

import com.step.jliang.enumerated.TaskType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jliang on 17/6/12.
 */
//@Data
public class Task {
    private final String id;
    private final String title;
    private final TaskType type;
    private final LocalDate createdOn;
    private boolean done = false;
    private Set<String> tags = new HashSet<>();
    private LocalDate dueOn;

    public Task(String id, TaskType type, LocalDate ld) {
        this.id = id;
        this.title = id;
        this.type = type;
        this.createdOn = ld;
    }

    public Task addTag(String tag) {
        tags.add(tag);
        return this;
    }

    public String getTitle() {

        return title;
    }

    public TaskType getType() {
        return type;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public LocalDate getDueOn() {
        return dueOn;
    }

    public void setDueOn(LocalDate dueOn) {
        this.dueOn = dueOn;
    }
}
