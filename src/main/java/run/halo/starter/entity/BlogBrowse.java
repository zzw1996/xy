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
    kind = "BlogBrowse",
    plural = "blogbrowse",
    singular = "blogbrowse")
public class BlogBrowse extends AbstractExtension {
}
