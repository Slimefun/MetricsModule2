package dev.walshy.sfmetrics.charts;

import javax.annotation.Nonnull;

import org.bstats.charts.SimplePie;
import org.bstats.json.JsonObjectBuilder;
import org.bukkit.Server;

import dev.walshy.sfmetrics.MetricsModule;
import dev.walshy.sfmetrics.VersionDependentChart;
import io.github.thebusybiscuit.slimefun4.api.SlimefunBranch;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

/**
 * This {@link SimplePie} shows us whether a {@link Server} has enabled or disabled
 * automatic updates for our {@link MetricsModule}.
 * 
 * @author Walshy
 *
 */
public class MetricsAutoUpdatesChart extends SimplePie implements VersionDependentChart {

    public MetricsAutoUpdatesChart() {
        super("metrics_auto_updates", () -> {
            boolean enabled = Slimefun.getMetricsService().hasAutoUpdates();
            return enabled ? "enabled" : "disabled";
        });
    }

    @Override
    public boolean isCompatible(@Nonnull SlimefunBranch branch, int build) {
        if (branch == SlimefunBranch.DEVELOPMENT) {
            return build >= 600;
        } else if (branch == SlimefunBranch.STABLE) {
            return build >= 15;
        } else {
            return false;
        }
    }

    @Nonnull
    @Override
    public String getName() {
        return "Metrics Auto Updates";
    }

    @Nonnull
    @Override
    public JsonObjectBuilder.JsonObject getDataSample() throws Exception {
        return getChartData();
    }
}
