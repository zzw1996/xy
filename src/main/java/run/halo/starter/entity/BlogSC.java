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
    kind = "BlogSC",
    plural = "blogsc",
    singular = "blogsc")
public class BlogSC extends AbstractExtension {
}
