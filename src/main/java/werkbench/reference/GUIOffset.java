package werkbench.reference;

import jakimbox.prefab.gui.Tabs.TabSide;

/**
 *
 * @author jakimfett
 */
public enum GUIOffset
{
    CHEST_DOUBLE(new int[]
    {
        0, 0
    }, new int[]
    {
        54, 0
    }), CHEST_SINGLE(new int[]
    {
        54, 0
    }, new int[]
    {
        0, 0
    });

    private final int[] leftOffset;
    private final int[] rightOffset;

    GUIOffset(int[] leftOffset, int[] rightOffset)
    {
        this.leftOffset = leftOffset;
        this.rightOffset = rightOffset;
    }

    /**
     * Get the offset for a given side
     *
     * @param side
     * @return int[] offsets, x/y
     */
    public int[] getOffsetForSide(TabSide side)
    {
        if (side == TabSide.LEFT)
        {
            return this.leftOffset;
        }
        return this.rightOffset;
    }

}
