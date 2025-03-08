package run.halo.starter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.extension.AbstractExtension;
import run.halo.app.extension.GVK;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@GVK(group = "xy.halo.run",
    version = "v1alpha1",
    kind = "BlogVote",
    plural = "blogvote",
    singular = "blogvote")
public class BlogVote extends AbstractExtension {
}
