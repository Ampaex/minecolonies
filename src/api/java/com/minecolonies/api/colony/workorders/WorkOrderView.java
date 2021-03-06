package com.minecolonies.api.colony.workorders;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * The WorkOrderView is the client-side representation of a WorkOrders. Views contain the WorkOrder's data that is relevant to a Client, in a more client-friendly form Mutable
 * operations on a View result in a message to the server to perform the operation
 */
public class WorkOrderView
{
    /**
     * The work orders id.
     */
    private int id;

    /**
     * The priority.
     */
    private int priority;

    /**
     * Its description.
     */
    private String value;

    /**
     * The type (defined by an enum).
     */
    private WorkOrderType type;

    /**
     * Claimed by building id pos.
     */
    private BlockPos claimedBy;

    /**
     * Position where its being built at.
     */
    private BlockPos pos;

    /**
     * Priority getter.
     *
     * @return the priority.
     */
    public int getPriority()
    {
        return priority;
    }

    /**
     * Setter for the priority.
     *
     * @param priority the new priority.
     */
    public void setPriority(final int priority)
    {
        this.priority = priority;
    }

    /**
     * Value getter.
     *
     * @return the value String.
     */
    public String get()
    {
        return value.replaceAll("schematics/(?:decorations/)?", "");
    }

    /**
     * Type getter.
     *
     * @return the type (defined by Enum).
     */
    public WorkOrderType getType()
    {
        return type;
    }

    /**
     * Id getter.
     *
     * @return the id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Id setter.
     *
     * @param id the id to set.
     */
    public void setId(final int id)
    {
        this.id = id;
    }

    /**
     * ClaimedBy getter.
     *
     * @return citizen id who claimed the workOrder.
     */
    public BlockPos getClaimedBy()
    {
        return claimedBy;
    }

    /**
     * Deserialize the attributes and variables from transition. Buffer may be not readable because the workOrderView may be null.
     *
     * @param buf Byte buffer to deserialize.
     */
    public void deserialize(@NotNull final PacketBuffer buf)
    {
        id = buf.readInt();
        priority = buf.readInt();
        claimedBy = buf.readBlockPos();
        type = WorkOrderType.values()[buf.readInt()];
        value = buf.readString(32767);
        pos = buf.readBlockPos();
    }

    /**
     * Get the position of the workorder.
     *
     * @return the position
     */
    public BlockPos getPos()
    {
        return this.pos;
    }
}
