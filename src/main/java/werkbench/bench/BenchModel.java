package werkbench.bench;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class BenchModel extends ModelBase
{
    ModelRenderer Top1;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer bar1;
    ModelRenderer bar2;
    ModelRenderer bar3;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer bottom1;
    ModelRenderer bottom2;
    ModelRenderer bottom3;
    ModelRenderer bottom4;
    ModelRenderer bottom5;
    ModelRenderer structure1;
    ModelRenderer structure2;
    ModelRenderer structure3;
    ModelRenderer cajon1;
    ModelRenderer cajon1m1;
    ModelRenderer cajon1m2;
    ModelRenderer cajon1front1;
    ModelRenderer cajon1front2;
    ModelRenderer cajon1front3;
    ModelRenderer cajon2;
    ModelRenderer cajon2m1;
    ModelRenderer cajon2m2;
    ModelRenderer cajon2front1;
    ModelRenderer cajon2front2;
    ModelRenderer cajon2front3;
    ModelRenderer cajon3;
    ModelRenderer cajon3m1;
    ModelRenderer cajon3m2;
    ModelRenderer cajon3front1;
    ModelRenderer cajon3front2;
    ModelRenderer cajon3front3;
    ModelRenderer cajon4;
    ModelRenderer cajon4m1;
    ModelRenderer cajon4m2;
    ModelRenderer cajon4front1;
    ModelRenderer cajon4front2;
    ModelRenderer cajon4front3;
    ModelRenderer door1;
    ModelRenderer knob1;
    ModelRenderer door2;
    ModelRenderer knob2;
    ModelRenderer hook;
    ModelRenderer saw2;
    ModelRenderer saw3;
    ModelRenderer saw4;
    ModelRenderer saw5;
    ModelRenderer saw6;
    ModelRenderer saw7;
    ModelRenderer saw8;
    ModelRenderer saw;
    ModelRenderer hammer1;
    ModelRenderer hammer2;
    ModelRenderer hammer3;
    ModelRenderer hammer4;
    ModelRenderer hammer5;
    ModelRenderer hammer6;
    ModelRenderer hammer7;
    ModelRenderer hook2;
    ModelRenderer hook3;
    ModelRenderer escuadra;
    ModelRenderer escuadra2;
    ModelRenderer hook4;
    ModelRenderer press;
    ModelRenderer wheel1;
    ModelRenderer wheel1m1;
    ModelRenderer wheel1m2;
    ModelRenderer wheel1m3;
    ModelRenderer wheel1m4;
    ModelRenderer wheel1m5;
    ModelRenderer wheel1screw2;
    ModelRenderer wheel1screw1;
    ModelRenderer wheel1screw3;
    ModelRenderer wheel1screw;
    ModelRenderer wheel2;
    ModelRenderer wheel2m1;
    ModelRenderer wheel2m2;
    ModelRenderer wheel2m3;
    ModelRenderer wheel2m4;
    ModelRenderer wheel2m5;
    ModelRenderer wheel2screw2;
    ModelRenderer wheel2screw1;
    ModelRenderer wheel2screw3;
    ModelRenderer wheel2screw;
    ModelRenderer upper;
    ModelRenderer upper1;
    ModelRenderer upper2;
    ModelRenderer upper2m1;
    ModelRenderer upper2m2;
    ModelRenderer upper3;
    ModelRenderer upper4;
    ModelRenderer upper5;
    ModelRenderer upper9;
    ModelRenderer upperC1;
    ModelRenderer knob3;
    ModelRenderer upper6;
    ModelRenderer upper6m1;
    ModelRenderer upper6m2;
    ModelRenderer upper7;
    ModelRenderer upper7m1;
    ModelRenderer upper7m2;
    ModelRenderer upper8;
    ModelRenderer upper8m1;
    ModelRenderer upper8m2;
    ModelRenderer cajon5;
    ModelRenderer knob4;
    ModelRenderer C5m1;
    ModelRenderer C5m2;
    ModelRenderer C5m3;
    ModelRenderer C5m5;
    ModelRenderer C5m4;
    ModelRenderer C5m6;
    ModelRenderer cajon5a1;
    ModelRenderer cajon6;
    ModelRenderer knob5;
    ModelRenderer cajon7;
    ModelRenderer knob6;
    ModelRenderer upper91;
    ModelRenderer upper10;
    ModelRenderer upper11;

    public BenchModel()
    {
        Top1 = new ModelRenderer(this, 0, 91);
        Top1.setTextureSize(256, 256);
        Top1.addBox(-16F, -2.5F, -16F, 32, 5, 32);
        Top1.setRotationPoint(0F, -6F, 0F);
        Leg1 = new ModelRenderer(this, 6, 87);
        Leg1.setTextureSize(256, 256);
        Leg1.addBox(-2F, -13.5F, -2F, 4, 27, 4);
        Leg1.setRotationPoint(-14F, 10F, -14F);
        Leg2 = new ModelRenderer(this, 6, 87);
        Leg2.setTextureSize(256, 256);
        Leg2.addBox(-2F, -13.5F, -2F, 4, 27, 4);
        Leg2.setRotationPoint(-14F, 10F, 14F);
        bar1 = new ModelRenderer(this, 35, 59);
        bar1.setTextureSize(256, 256);
        bar1.addBox(-2F, -1F, -12F, 4, 2, 24);
        bar1.setRotationPoint(-14F, 21F, 0F);
        bar2 = new ModelRenderer(this, 35, 59);
        bar2.setTextureSize(256, 256);
        bar2.addBox(-2F, -1F, -12F, 4, 2, 24);
        bar2.setRotationPoint(14F, 21F, 0F);
        bar3 = new ModelRenderer(this, 31, 52);
        bar3.setTextureSize(256, 256);
        bar3.addBox(-12F, -1F, -2F, 24, 2, 4);
        bar3.setRotationPoint(0F, 21F, 0F);
        Leg3 = new ModelRenderer(this, 6, 87);
        Leg3.setTextureSize(256, 256);
        Leg3.addBox(-2F, -13.5F, -2F, 4, 27, 4);
        Leg3.setRotationPoint(14F, 10F, -14F);
        Leg4 = new ModelRenderer(this, 6, 87);
        Leg4.setTextureSize(256, 256);
        Leg4.addBox(-2F, -13.5F, -2F, 4, 27, 4);
        Leg4.setRotationPoint(14F, 10F, 14F);
        bottom1 = new ModelRenderer(this, 178, 129);
        bottom1.setTextureSize(256, 256);
        bottom1.addBox(-1F, -9.5F, -14F, 2, 19, 28);
        bottom1.setRotationPoint(12F, 9.5F, 0F);
        bottom2 = new ModelRenderer(this, 178, 129);
        bottom2.setTextureSize(256, 256);
        bottom2.addBox(-1F, -9.5F, -14F, 2, 19, 28);
        bottom2.setRotationPoint(-12F, 9.5F, 0F);
        bottom3 = new ModelRenderer(this, 156, 176);
        bottom3.setTextureSize(256, 256);
        bottom3.addBox(-11F, -0.5F, -14F, 22, 1, 28);
        bottom3.setRotationPoint(0F, 0.5F, 0F);
        bottom4 = new ModelRenderer(this, 176, 205);
        bottom4.setTextureSize(256, 256);
        bottom4.addBox(-12F, -10F, -1F, 24, 20, 2);
        bottom4.setRotationPoint(0F, 10F, 15F);
        bottom5 = new ModelRenderer(this, 148, 227);
        bottom5.setTextureSize(256, 256);
        bottom5.addBox(-13F, -0.5F, -14F, 26, 1, 28);
        bottom5.setRotationPoint(0F, 19.5F, 0F);
        structure1 = new ModelRenderer(this, 180, 83);
        structure1.setTextureSize(256, 256);
        structure1.addBox(-0.5F, -9F, -14F, 1, 18, 28);
        structure1.setRotationPoint(-2.5F, 10F, 0F);
        structure2 = new ModelRenderer(this, 156, 54);
        structure2.setTextureSize(256, 256);
        structure2.addBox(-11F, -0.5F, -14F, 22, 1, 28);
        structure2.setRotationPoint(0F, 6.5F, 0F);
        structure3 = new ModelRenderer(this, 170, 25);
        structure3.setTextureSize(256, 256);
        structure3.addBox(-4F, -0.5F, -14F, 8, 1, 28);
        structure3.setRotationPoint(-7F, 12.5F, 0F);
        cajon1 = new ModelRenderer(this, 120, 0);
        cajon1.setTextureSize(256, 256);
        cajon1.addBox(-0.5F, -2.5F, -14.5F, 1, 5, 29);
        cajon1.setRotationPoint(-10.5F, 3.5F, 0F);
        cajon1m1 = new ModelRenderer(this, 120, 0);
        cajon1m1.setTextureSize(256, 256);
        cajon1m1.addBox(-0.5F, -2.5F, -14.5F, 1, 5, 29);
        cajon1m1.setRotationPoint(-3.5F, 3.5F, 0F);
        cajon1m2 = new ModelRenderer(this, 114, 34);
        cajon1m2.setTextureSize(256, 256);
        cajon1m2.addBox(-3F, -0.5F, -14.5F, 6, 1, 29);
        cajon1m2.setRotationPoint(-7F, 5.5F, 0F);
        cajon1front1 = new ModelRenderer(this, 160, 22);
        cajon1front1.setTextureSize(256, 256);
        cajon1front1.addBox(-3F, -1F, -0.5F, 6, 2, 1);
        cajon1front1.setRotationPoint(-7F, 4F, -14F);
        cajon1front2 = new ModelRenderer(this, 160, 17);
        cajon1front2.setTextureSize(256, 256);
        cajon1front2.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon1front2.setRotationPoint(-9.5F, 2F, -14F);
        cajon1front3 = new ModelRenderer(this, 166, 17);
        cajon1front3.setTextureSize(256, 256);
        cajon1front3.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon1front3.setRotationPoint(-4.5F, 2F, -14F);
        cajon2 = new ModelRenderer(this, 120, 0);
        cajon2.setTextureSize(256, 256);
        cajon2.addBox(-0.5F, -2.5F, -14.5F, 1, 5, 29);
        cajon2.setRotationPoint(-10.5F, 9.5F, 0F);
        cajon2m1 = new ModelRenderer(this, 120, 0);
        cajon2m1.setTextureSize(256, 256);
        cajon2m1.addBox(-0.5F, -2.5F, -14.5F, 1, 5, 29);
        cajon2m1.setRotationPoint(-3.5F, 9.5F, 0F);
        cajon2m2 = new ModelRenderer(this, 114, 34);
        cajon2m2.setTextureSize(256, 256);
        cajon2m2.addBox(-3F, -0.5F, -14.5F, 6, 1, 29);
        cajon2m2.setRotationPoint(-7F, 11.5F, 0F);
        cajon2front1 = new ModelRenderer(this, 160, 22);
        cajon2front1.setTextureSize(256, 256);
        cajon2front1.addBox(-3F, -1F, -0.5F, 6, 2, 1);
        cajon2front1.setRotationPoint(-7F, 10F, -14F);
        cajon2front2 = new ModelRenderer(this, 160, 17);
        cajon2front2.setTextureSize(256, 256);
        cajon2front2.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon2front2.setRotationPoint(-9.5F, 8F, -14F);
        cajon2front3 = new ModelRenderer(this, 166, 17);
        cajon2front3.setTextureSize(256, 256);
        cajon2front3.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon2front3.setRotationPoint(-4.5F, 8F, -14F);
        cajon3 = new ModelRenderer(this, 120, 0);
        cajon3.setTextureSize(256, 256);
        cajon3.addBox(-0.5F, -3F, -14.5F, 1, 6, 29);
        cajon3.setRotationPoint(-10.5F, 16F, 0F);
        cajon3m1 = new ModelRenderer(this, 120, 0);
        cajon3m1.setTextureSize(256, 256);
        cajon3m1.addBox(-0.5F, -3F, -14.5F, 1, 6, 29);
        cajon3m1.setRotationPoint(-3.5F, 16F, 0F);
        cajon3m2 = new ModelRenderer(this, 114, 34);
        cajon3m2.setTextureSize(256, 256);
        cajon3m2.addBox(-3F, -0.5F, -14.5F, 6, 1, 29);
        cajon3m2.setRotationPoint(-7F, 18.5F, 0F);
        cajon3front1 = new ModelRenderer(this, 160, 22);
        cajon3front1.setTextureSize(256, 256);
        cajon3front1.addBox(-3F, -1.5F, -0.5F, 6, 3, 1);
        cajon3front1.setRotationPoint(-7F, 16.5F, -14F);
        cajon3front2 = new ModelRenderer(this, 160, 17);
        cajon3front2.setTextureSize(256, 256);
        cajon3front2.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon3front2.setRotationPoint(-9.5F, 14F, -14F);
        cajon3front3 = new ModelRenderer(this, 166, 17);
        cajon3front3.setTextureSize(256, 256);
        cajon3front3.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon3front3.setRotationPoint(-4.5F, 14F, -14F);
        cajon4 = new ModelRenderer(this, 120, 0);
        cajon4.setTextureSize(256, 256);
        cajon4.addBox(-0.5F, -2.5F, -14.5F, 1, 5, 29);
        cajon4.setRotationPoint(-1.5F, 3.5F, 0F);
        cajon4m1 = new ModelRenderer(this, 120, 0);
        cajon4m1.setTextureSize(256, 256);
        cajon4m1.addBox(-0.5F, -2.5F, -14.5F, 1, 5, 29);
        cajon4m1.setRotationPoint(10.5F, 3.5F, 0F);
        cajon4m2 = new ModelRenderer(this, 104, 34);
        cajon4m2.setTextureSize(256, 256);
        cajon4m2.addBox(-5.5F, -0.5F, -14.5F, 11, 1, 29);
        cajon4m2.setRotationPoint(4.5F, 5.5F, 0F);
        cajon4front1 = new ModelRenderer(this, 160, 22);
        cajon4front1.setTextureSize(256, 256);
        cajon4front1.addBox(-5.5F, -1F, -0.5F, 11, 2, 1);
        cajon4front1.setRotationPoint(4.5F, 4F, -14F);
        cajon4front2 = new ModelRenderer(this, 160, 17);
        cajon4front2.setTextureSize(256, 256);
        cajon4front2.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon4front2.setRotationPoint(-0.5F, 2F, -14F);
        cajon4front3 = new ModelRenderer(this, 166, 17);
        cajon4front3.setTextureSize(256, 256);
        cajon4front3.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        cajon4front3.setRotationPoint(9.5F, 2F, -14F);
        door1 = new ModelRenderer(this, 140, 70);
        door1.setTextureSize(256, 256);
        door1.addBox(-0.5F, -6F, -0.5F, 7, 12, 1);
        door1.setRotationPoint(-2F, 13F, -14F);
        knob1 = new ModelRenderer(this, 139, 65);
        knob1.setTextureSize(256, 256);
        knob1.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        knob1.setRotationPoint(2.980974F, 13F, -14.43578F);
        door2 = new ModelRenderer(this, 124, 70);
        door2.setTextureSize(256, 256);
        door2.addBox(-6.5F, -6F, -0.5F, 7, 12, 1);
        door2.setRotationPoint(11F, 13F, -14F);
        knob2 = new ModelRenderer(this, 139, 65);
        knob2.setTextureSize(256, 256);
        knob2.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        knob2.setRotationPoint(6.019026F, 13F, -14.43578F);
        hook = new ModelRenderer(this, 43, 4);
        hook.setTextureSize(256, 256);
        hook.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
        hook.setRotationPoint(-17.5F, -5F, 9F);
        saw2 = new ModelRenderer(this, 9, 9);
        saw2.setTextureSize(256, 256);
        saw2.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 7);
        saw2.setRotationPoint(-17F, -3F, 9F);
        saw3 = new ModelRenderer(this, 14, 14);
        saw3.setTextureSize(256, 256);
        saw3.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        saw3.setRotationPoint(-17F, -4.5F, 6.5F);
        saw4 = new ModelRenderer(this, 14, 14);
        saw4.setTextureSize(256, 256);
        saw4.addBox(-0.5F, -1F, -1F, 1, 2, 2);
        saw4.setRotationPoint(-17F, -4.5F, 11.5F);
        saw5 = new ModelRenderer(this, 9, 9);
        saw5.setTextureSize(256, 256);
        saw5.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 7);
        saw5.setRotationPoint(-17F, -6F, 9F);
        saw6 = new ModelRenderer(this, 14, 14);
        saw6.setTextureSize(256, 256);
        saw6.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        saw6.setRotationPoint(-17F, -7F, 11.5F);
        saw7 = new ModelRenderer(this, 14, 14);
        saw7.setTextureSize(256, 256);
        saw7.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        saw7.setRotationPoint(-17F, -7F, 6.5F);
        saw8 = new ModelRenderer(this, 15, 15);
        saw8.setTextureSize(256, 256);
        saw8.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        saw8.setRotationPoint(-17F, -7.5F, 12F);
        saw = new ModelRenderer(this, 43, 8);
        saw.setTextureSize(256, 256);
        saw.addBox(0F, -8.5F, -3F, 0, 17, 6);
        saw.setRotationPoint(-17F, 6F, 9F);
        hammer1 = new ModelRenderer(this, 56, 10);
        hammer1.setTextureSize(256, 256);
        hammer1.addBox(-1F, -1F, -0.5F, 2, 2, 1);
        hammer1.setRotationPoint(-17.5F, -6.5F, 3F);
        hammer2 = new ModelRenderer(this, 56, 10);
        hammer2.setTextureSize(256, 256);
        hammer2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        hammer2.setRotationPoint(-17.5F, -6.5F, 2F);
        hammer3 = new ModelRenderer(this, 54, 8);
        hammer3.setTextureSize(256, 256);
        hammer3.addBox(-1F, -1F, -1.5F, 2, 2, 3);
        hammer3.setRotationPoint(-17.5F, -6.5F, 0F);
        hammer4 = new ModelRenderer(this, 56, 10);
        hammer4.setTextureSize(256, 256);
        hammer4.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
        hammer4.setRotationPoint(-17.5F, -6.5F, -2F);
        hammer5 = new ModelRenderer(this, 56, 10);
        hammer5.setTextureSize(256, 256);
        hammer5.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
        hammer5.setRotationPoint(-17.5F, -5.5F, -3F);
        hammer6 = new ModelRenderer(this, 56, 10);
        hammer6.setTextureSize(256, 256);
        hammer6.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
        hammer6.setRotationPoint(-17.5F, -6F, -2.5F);
        hammer7 = new ModelRenderer(this, 56, 18);
        hammer7.setTextureSize(256, 256);
        hammer7.addBox(-1F, -6F, -1F, 2, 12, 2);
        hammer7.setRotationPoint(-17.5F, 0.5F, 0F);
        hook2 = new ModelRenderer(this, 43, 4);
        hook2.setTextureSize(256, 256);
        hook2.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
        hook2.setRotationPoint(-17.5F, -5F, 2F);
        hook3 = new ModelRenderer(this, 43, 4);
        hook3.setTextureSize(256, 256);
        hook3.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
        hook3.setRotationPoint(-17.5F, -5F, -1.8F);
        escuadra = new ModelRenderer(this, 36, 33);
        escuadra.setTextureSize(256, 256);
        escuadra.addBox(-0.5F, -1F, -3F, 1, 2, 6);
        escuadra.setRotationPoint(-17.5F, -6.5F, -8F);
        escuadra2 = new ModelRenderer(this, 37, 24);
        escuadra2.setTextureSize(256, 256);
        escuadra2.addBox(0F, -5F, -1F, 0, 10, 2);
        escuadra2.setRotationPoint(-17.5F, -0.5F, -10F);
        hook4 = new ModelRenderer(this, 43, 4);
        hook4.setTextureSize(256, 256);
        hook4.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
        hook4.setRotationPoint(-17.5F, -5F, -8F);
        press = new ModelRenderer(this, 78, 21);
        press.setTextureSize(256, 256);
        press.addBox(-16F, -2.5F, -1F, 32, 5, 2);
        press.setRotationPoint(0F, -6F, -19F);
        wheel1 = new ModelRenderer(this, 72, 7);
        wheel1.setTextureSize(256, 256);
        wheel1.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel1.setRotationPoint(-11F, -6F, -21.5F);
        wheel1m1 = new ModelRenderer(this, 72, 7);
        wheel1m1.setTextureSize(256, 256);
        wheel1m1.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel1m1.setRotationPoint(-11F, -6F, -21.5F);
        wheel1m2 = new ModelRenderer(this, 72, 7);
        wheel1m2.setTextureSize(256, 256);
        wheel1m2.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel1m2.setRotationPoint(-11F, -6F, -21.5F);
        wheel1m3 = new ModelRenderer(this, 72, 7);
        wheel1m3.setTextureSize(256, 256);
        wheel1m3.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel1m3.setRotationPoint(-11F, -6F, -21.5F);
        wheel1m4 = new ModelRenderer(this, 72, 7);
        wheel1m4.setTextureSize(256, 256);
        wheel1m4.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel1m4.setRotationPoint(-11F, -6F, -21.5F);
        wheel1m5 = new ModelRenderer(this, 72, 7);
        wheel1m5.setTextureSize(256, 256);
        wheel1m5.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel1m5.setRotationPoint(-11F, -6F, -21.5F);
        wheel1screw2 = new ModelRenderer(this, 72, 7);
        wheel1screw2.setTextureSize(256, 256);
        wheel1screw2.addBox(-2F, -0.5F, -0.5F, 2, 1, 1);
        wheel1screw2.setRotationPoint(-11F, -6F, -20.8F);
        wheel1screw1 = new ModelRenderer(this, 72, 7);
        wheel1screw1.setTextureSize(256, 256);
        wheel1screw1.addBox(-2F, -0.5F, -0.5F, 2, 1, 1);
        wheel1screw1.setRotationPoint(-11F, -6F, -20.8F);
        wheel1screw3 = new ModelRenderer(this, 72, 7);
        wheel1screw3.setTextureSize(256, 256);
        wheel1screw3.addBox(-2F, -0.5F, -0.5F, 2, 1, 1);
        wheel1screw3.setRotationPoint(-11F, -6.000002F, -20.8F);
        wheel1screw = new ModelRenderer(this, 80, 3);
        wheel1screw.setTextureSize(256, 256);
        wheel1screw.addBox(-0.5F, -0.5F, -3F, 1, 1, 6);
        wheel1screw.setRotationPoint(-11F, -6F, -18.5F);
        wheel2 = new ModelRenderer(this, 72, 7);
        wheel2.setTextureSize(256, 256);
        wheel2.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel2.setRotationPoint(11F, -6F, -21.5F);
        wheel2m1 = new ModelRenderer(this, 72, 7);
        wheel2m1.setTextureSize(256, 256);
        wheel2m1.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel2m1.setRotationPoint(11F, -6F, -21.5F);
        wheel2m2 = new ModelRenderer(this, 72, 7);
        wheel2m2.setTextureSize(256, 256);
        wheel2m2.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel2m2.setRotationPoint(11F, -6F, -21.5F);
        wheel2m3 = new ModelRenderer(this, 72, 7);
        wheel2m3.setTextureSize(256, 256);
        wheel2m3.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel2m3.setRotationPoint(11F, -6F, -21.5F);
        wheel2m4 = new ModelRenderer(this, 72, 7);
        wheel2m4.setTextureSize(256, 256);
        wheel2m4.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel2m4.setRotationPoint(11F, -6F, -21.5F);
        wheel2m5 = new ModelRenderer(this, 72, 7);
        wheel2m5.setTextureSize(256, 256);
        wheel2m5.addBox(-2.5F, -1.5F, -0.5F, 1, 3, 1);
        wheel2m5.setRotationPoint(11F, -6F, -21.5F);
        wheel2screw2 = new ModelRenderer(this, 72, 7);
        wheel2screw2.setTextureSize(256, 256);
        wheel2screw2.addBox(-2F, -0.5F, -0.5F, 2, 1, 1);
        wheel2screw2.setRotationPoint(11F, -6F, -20.8F);
        wheel2screw1 = new ModelRenderer(this, 72, 7);
        wheel2screw1.setTextureSize(256, 256);
        wheel2screw1.addBox(-2F, -0.5F, -0.5F, 2, 1, 1);
        wheel2screw1.setRotationPoint(11F, -6F, -20.8F);
        wheel2screw3 = new ModelRenderer(this, 72, 7);
        wheel2screw3.setTextureSize(256, 256);
        wheel2screw3.addBox(-2F, -0.5F, -0.5F, 2, 1, 1);
        wheel2screw3.setRotationPoint(11F, -6.000002F, -20.8F);
        wheel2screw = new ModelRenderer(this, 80, 3);
        wheel2screw.setTextureSize(256, 256);
        wheel2screw.addBox(-0.5F, -0.5F, -3F, 1, 1, 6);
        wheel2screw.setRotationPoint(11F, -6F, -18.5F);
        upper = new ModelRenderer(this, 0, 244);
        upper.setTextureSize(256, 256);
        upper.addBox(-16F, -5.5F, -0.5F, 32, 11, 1);
        upper.setRotationPoint(0F, -14F, 14F);
        upper1 = new ModelRenderer(this, 67, 246);
        upper1.setTextureSize(256, 256);
        upper1.addBox(-15F, -0.5F, -4.5F, 30, 1, 9);
        upper1.setRotationPoint(0F, -14F, 9F);
        upper2 = new ModelRenderer(this, 66, 229);
        upper2.setTextureSize(256, 256);
        upper2.addBox(-0.5F, -1.5F, -4.5F, 1, 3, 9);
        upper2.setRotationPoint(-9.5F, -16F, 9F);
        upper2m1 = new ModelRenderer(this, 87, 233);
        upper2m1.setTextureSize(256, 256);
        upper2m1.addBox(-0.5F, -1F, -2F, 1, 2, 4);
        upper2m1.setRotationPoint(-9.5F, -18.5F, 11.5F);
        upper2m2 = new ModelRenderer(this, 85, 240);
        upper2m2.setTextureSize(256, 256);
        upper2m2.addBox(-0.5F, -1F, -1.5F, 1, 2, 3);
        upper2m2.setRotationPoint(-9.5F, -17.75F, 9.15F);
        upper3 = new ModelRenderer(this, 0, 224);
        upper3.setTextureSize(256, 256);
        upper3.addBox(-0.5F, -5.5F, -4.5F, 1, 11, 9);
        upper3.setRotationPoint(-15.5F, -14F, 9F);
        upper4 = new ModelRenderer(this, 0, 224);
        upper4.setTextureSize(256, 256);
        upper4.addBox(-0.5F, -5.5F, -4.5F, 1, 11, 9);
        upper4.setRotationPoint(15.5F, -14F, 9F);
        upper5 = new ModelRenderer(this, 70, 242);
        upper5.setTextureSize(256, 256);
        upper5.addBox(-2.5F, -1.5F, -0.5F, 5, 3, 1);
        upper5.setRotationPoint(-12.5F, -16F, 5F);
        upper9 = new ModelRenderer(this, 97, 239);
        upper9.setTextureSize(256, 256);
        upper9.addBox(-6F, -0.5F, -2.5F, 12, 1, 5);
        upper9.setRotationPoint(9F, -17.5F, 11F);
        upperC1 = new ModelRenderer(this, 35, 234);
        upperC1.setTextureSize(256, 256);
        upperC1.addBox(-6F, -1.5F, -0.5F, 12, 3, 1);
        upperC1.setRotationPoint(9.3F, -16F, 8.5F);
        knob3 = new ModelRenderer(this, 72, 7);
        knob3.setTextureSize(256, 256);
        knob3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        knob3.setRotationPoint(9F, -16F, 8F);
        upper6 = new ModelRenderer(this, 66, 229);
        upper6.setTextureSize(256, 256);
        upper6.addBox(-0.5F, -1.5F, -4.5F, 1, 3, 9);
        upper6.setRotationPoint(3F, -16F, 9F);
        upper6m1 = new ModelRenderer(this, 87, 233);
        upper6m1.setTextureSize(256, 256);
        upper6m1.addBox(-0.5F, -1F, -2F, 1, 2, 4);
        upper6m1.setRotationPoint(3F, -18.5F, 11.5F);
        upper6m2 = new ModelRenderer(this, 85, 240);
        upper6m2.setTextureSize(256, 256);
        upper6m2.addBox(-0.5F, -1F, -1.5F, 1, 2, 3);
        upper6m2.setRotationPoint(3F, -17.75F, 9.15F);
        upper7 = new ModelRenderer(this, 66, 229);
        upper7.setTextureSize(256, 256);
        upper7.addBox(-0.5F, -1.5F, -4.5F, 1, 3, 9);
        upper7.setRotationPoint(-5F, -16F, 9F);
        upper7m1 = new ModelRenderer(this, 87, 233);
        upper7m1.setTextureSize(256, 256);
        upper7m1.addBox(-0.5F, -1F, -2F, 1, 2, 4);
        upper7m1.setRotationPoint(-5F, -18.5F, 11.5F);
        upper7m2 = new ModelRenderer(this, 85, 240);
        upper7m2.setTextureSize(256, 256);
        upper7m2.addBox(-0.5F, -1F, -1.5F, 1, 2, 3);
        upper7m2.setRotationPoint(-5F, -17.75F, 9.15F);
        upper8 = new ModelRenderer(this, 66, 229);
        upper8.setTextureSize(256, 256);
        upper8.addBox(-0.5F, -1.5F, -4.5F, 1, 3, 9);
        upper8.setRotationPoint(-1F, -16F, 9F);
        upper8m1 = new ModelRenderer(this, 87, 233);
        upper8m1.setTextureSize(256, 256);
        upper8m1.addBox(-0.5F, -1F, -2F, 1, 2, 4);
        upper8m1.setRotationPoint(-1F, -18.5F, 11.5F);
        upper8m2 = new ModelRenderer(this, 85, 240);
        upper8m2.setTextureSize(256, 256);
        upper8m2.addBox(-0.5F, -1F, -1.5F, 1, 2, 3);
        upper8m2.setRotationPoint(-1F, -17.75F, 9.15F);
        cajon5 = new ModelRenderer(this, 30, 214);
        cajon5.setTextureSize(256, 256);
        cajon5.addBox(-2.5F, -2F, -0.5F, 5, 4, 1);
        cajon5.setRotationPoint(11.5F, -11.5F, 4.5F);
        knob4 = new ModelRenderer(this, 72, 7);
        knob4.setTextureSize(256, 256);
        knob4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        knob4.setRotationPoint(11.5F, -11.5F, 4F);
        C5m1 = new ModelRenderer(this, 44, 214);
        C5m1.setTextureSize(256, 256);
        C5m1.addBox(-0.5F, -2.5F, -2.5F, 1, 5, 5);
        C5m1.setRotationPoint(14.5F, -11F, 7F);
        C5m2 = new ModelRenderer(this, 44, 214);
        C5m2.setTextureSize(256, 256);
        C5m2.addBox(-0.5F, -2.5F, -2.5F, 1, 5, 5);
        C5m2.setRotationPoint(3F, -11F, 7F);
        C5m3 = new ModelRenderer(this, 44, 214);
        C5m3.setTextureSize(256, 256);
        C5m3.addBox(-0.5F, -2.5F, -2.5F, 1, 5, 5);
        C5m3.setRotationPoint(8.5F, -11F, 7F);
        C5m5 = new ModelRenderer(this, 51, 208);
        C5m5.setTextureSize(256, 256);
        C5m5.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 5);
        C5m5.setRotationPoint(5.75F, -11.5F, 7F);
        C5m4 = new ModelRenderer(this, 51, 208);
        C5m4.setTextureSize(256, 256);
        C5m4.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 5);
        C5m4.setRotationPoint(5.75F, -9F, 7F);
        C5m6 = new ModelRenderer(this, 51, 208);
        C5m6.setTextureSize(256, 256);
        C5m6.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 5);
        C5m6.setRotationPoint(11.5F, -9F, 7F);
        cajon5a1 = new ModelRenderer(this, 30, 217);
        cajon5a1.setTextureSize(256, 256);
        cajon5a1.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
        cajon5a1.setRotationPoint(11.5F, -9.5F, 4.5F);
        cajon6 = new ModelRenderer(this, 30, 214);
        cajon6.setTextureSize(256, 256);
        cajon6.addBox(-2.5F, -1F, -0.5F, 5, 2, 1);
        cajon6.setRotationPoint(5.66F, -12.5F, 4.5F);
        knob5 = new ModelRenderer(this, 72, 7);
        knob5.setTextureSize(256, 256);
        knob5.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        knob5.setRotationPoint(5.5F, -12.5F, 4F);
        cajon7 = new ModelRenderer(this, 30, 214);
        cajon7.setTextureSize(256, 256);
        cajon7.addBox(-2.5F, -1F, -0.5F, 5, 2, 1);
        cajon7.setRotationPoint(5.66F, -10F, 4.5F);
        knob6 = new ModelRenderer(this, 72, 7);
        knob6.setTextureSize(256, 256);
        knob6.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        knob6.setRotationPoint(5.5F, -10F, 4F);
        upper91 = new ModelRenderer(this, 86, 232);
        upper91.setTextureSize(256, 256);
        upper91.addBox(-0.5F, -1F, -2.5F, 1, 2, 5);
        upper91.setRotationPoint(6.5F, -18.5F, 11.01F);
        upper10 = new ModelRenderer(this, 86, 232);
        upper10.setTextureSize(256, 256);
        upper10.addBox(-0.5F, -1F, -2.5F, 1, 2, 5);
        upper10.setRotationPoint(9.25F, -18.5F, 11.01F);
        upper11 = new ModelRenderer(this, 86, 232);
        upper11.setTextureSize(256, 256);
        upper11.addBox(-0.5F, -1F, -2.5F, 1, 2, 5);
        upper11.setRotationPoint(12F, -18.5F, 11.01F);
    }

    public void render(float rotation)
    {
        Top1.rotateAngleX = 0F;
        Top1.rotateAngleY = 0F;
        Top1.rotateAngleZ = 0F;
        Top1.renderWithRotation(rotation);

        Leg1.rotateAngleX = 0F;
        Leg1.rotateAngleY = 0F;
        Leg1.rotateAngleZ = 0F;
        Leg1.renderWithRotation(rotation);

        Leg2.rotateAngleX = 0F;
        Leg2.rotateAngleY = 0F;
        Leg2.rotateAngleZ = 0F;
        Leg2.renderWithRotation(rotation);

        bar1.rotateAngleX = 0F;
        bar1.rotateAngleY = 0F;
        bar1.rotateAngleZ = 0F;
        bar1.renderWithRotation(rotation);

        bar2.rotateAngleX = 0F;
        bar2.rotateAngleY = 0F;
        bar2.rotateAngleZ = 0F;
        bar2.renderWithRotation(rotation);

        bar3.rotateAngleX = 0F;
        bar3.rotateAngleY = 0F;
        bar3.rotateAngleZ = 0F;
        bar3.renderWithRotation(rotation);

        Leg3.rotateAngleX = 0F;
        Leg3.rotateAngleY = 0F;
        Leg3.rotateAngleZ = 0F;
        Leg3.renderWithRotation(rotation);

        Leg4.rotateAngleX = 0F;
        Leg4.rotateAngleY = 0F;
        Leg4.rotateAngleZ = 0F;
        Leg4.renderWithRotation(rotation);

        bottom1.rotateAngleX = 0F;
        bottom1.rotateAngleY = 0F;
        bottom1.rotateAngleZ = 0F;
        bottom1.renderWithRotation(rotation);

        bottom2.rotateAngleX = 0F;
        bottom2.rotateAngleY = 0F;
        bottom2.rotateAngleZ = 0F;
        bottom2.renderWithRotation(rotation);

        bottom3.rotateAngleX = 0F;
        bottom3.rotateAngleY = 0F;
        bottom3.rotateAngleZ = 0F;
        bottom3.renderWithRotation(rotation);

        bottom4.rotateAngleX = 0F;
        bottom4.rotateAngleY = 0F;
        bottom4.rotateAngleZ = 0F;
        bottom4.renderWithRotation(rotation);

        bottom5.rotateAngleX = 0F;
        bottom5.rotateAngleY = 0F;
        bottom5.rotateAngleZ = 0F;
        bottom5.renderWithRotation(rotation);

        structure1.rotateAngleX = 0F;
        structure1.rotateAngleY = 0F;
        structure1.rotateAngleZ = 0F;
        structure1.renderWithRotation(rotation);

        structure2.rotateAngleX = 0F;
        structure2.rotateAngleY = 0F;
        structure2.rotateAngleZ = 0F;
        structure2.renderWithRotation(rotation);

        structure3.rotateAngleX = 0F;
        structure3.rotateAngleY = 0F;
        structure3.rotateAngleZ = 0F;
        structure3.renderWithRotation(rotation);

        cajon1.rotateAngleX = 0F;
        cajon1.rotateAngleY = 0F;
        cajon1.rotateAngleZ = 0F;
        cajon1.renderWithRotation(rotation);

        cajon1m1.rotateAngleX = 0F;
        cajon1m1.rotateAngleY = 0F;
        cajon1m1.rotateAngleZ = 0F;
        cajon1m1.renderWithRotation(rotation);

        cajon1m2.rotateAngleX = 0F;
        cajon1m2.rotateAngleY = 0F;
        cajon1m2.rotateAngleZ = 0F;
        cajon1m2.renderWithRotation(rotation);

        cajon1front1.rotateAngleX = 0F;
        cajon1front1.rotateAngleY = 0F;
        cajon1front1.rotateAngleZ = 0F;
        cajon1front1.renderWithRotation(rotation);

        cajon1front2.rotateAngleX = 0F;
        cajon1front2.rotateAngleY = 0F;
        cajon1front2.rotateAngleZ = 0F;
        cajon1front2.renderWithRotation(rotation);

        cajon1front3.rotateAngleX = 0F;
        cajon1front3.rotateAngleY = 0F;
        cajon1front3.rotateAngleZ = 0F;
        cajon1front3.renderWithRotation(rotation);

        cajon2.rotateAngleX = 0F;
        cajon2.rotateAngleY = 0F;
        cajon2.rotateAngleZ = 0F;
        cajon2.renderWithRotation(rotation);

        cajon2m1.rotateAngleX = 0F;
        cajon2m1.rotateAngleY = 0F;
        cajon2m1.rotateAngleZ = 0F;
        cajon2m1.renderWithRotation(rotation);

        cajon2m2.rotateAngleX = 0F;
        cajon2m2.rotateAngleY = 0F;
        cajon2m2.rotateAngleZ = 0F;
        cajon2m2.renderWithRotation(rotation);

        cajon2front1.rotateAngleX = 0F;
        cajon2front1.rotateAngleY = 0F;
        cajon2front1.rotateAngleZ = 0F;
        cajon2front1.renderWithRotation(rotation);

        cajon2front2.rotateAngleX = 0F;
        cajon2front2.rotateAngleY = 0F;
        cajon2front2.rotateAngleZ = 0F;
        cajon2front2.renderWithRotation(rotation);

        cajon2front3.rotateAngleX = 0F;
        cajon2front3.rotateAngleY = 0F;
        cajon2front3.rotateAngleZ = 0F;
        cajon2front3.renderWithRotation(rotation);

        cajon3.rotateAngleX = 0F;
        cajon3.rotateAngleY = 0F;
        cajon3.rotateAngleZ = 0F;
        cajon3.renderWithRotation(rotation);

        cajon3m1.rotateAngleX = 0F;
        cajon3m1.rotateAngleY = 0F;
        cajon3m1.rotateAngleZ = 0F;
        cajon3m1.renderWithRotation(rotation);

        cajon3m2.rotateAngleX = 0F;
        cajon3m2.rotateAngleY = 0F;
        cajon3m2.rotateAngleZ = 0F;
        cajon3m2.renderWithRotation(rotation);

        cajon3front1.rotateAngleX = 0F;
        cajon3front1.rotateAngleY = 0F;
        cajon3front1.rotateAngleZ = 0F;
        cajon3front1.renderWithRotation(rotation);

        cajon3front2.rotateAngleX = 0F;
        cajon3front2.rotateAngleY = 0F;
        cajon3front2.rotateAngleZ = 0F;
        cajon3front2.renderWithRotation(rotation);

        cajon3front3.rotateAngleX = 0F;
        cajon3front3.rotateAngleY = 0F;
        cajon3front3.rotateAngleZ = 0F;
        cajon3front3.renderWithRotation(rotation);

        cajon4.rotateAngleX = 0F;
        cajon4.rotateAngleY = 0F;
        cajon4.rotateAngleZ = 0F;
        cajon4.renderWithRotation(rotation);

        cajon4m1.rotateAngleX = 0F;
        cajon4m1.rotateAngleY = 0F;
        cajon4m1.rotateAngleZ = 0F;
        cajon4m1.renderWithRotation(rotation);

        cajon4m2.rotateAngleX = 0F;
        cajon4m2.rotateAngleY = 0F;
        cajon4m2.rotateAngleZ = 0F;
        cajon4m2.renderWithRotation(rotation);

        cajon4front1.rotateAngleX = 0F;
        cajon4front1.rotateAngleY = 0F;
        cajon4front1.rotateAngleZ = 0F;
        cajon4front1.renderWithRotation(rotation);

        cajon4front2.rotateAngleX = 0F;
        cajon4front2.rotateAngleY = 0F;
        cajon4front2.rotateAngleZ = 0F;
        cajon4front2.renderWithRotation(rotation);

        cajon4front3.rotateAngleX = 0F;
        cajon4front3.rotateAngleY = 0F;
        cajon4front3.rotateAngleZ = 0F;
        cajon4front3.renderWithRotation(rotation);

        door1.rotateAngleX = 0F;
        door1.rotateAngleY = 0.08726647F;
        door1.rotateAngleZ = 0F;
        door1.renderWithRotation(rotation);

        knob1.rotateAngleX = 0F;
        knob1.rotateAngleY = 0.08726647F;
        knob1.rotateAngleZ = 0F;
        knob1.renderWithRotation(rotation);

        door2.rotateAngleX = 0F;
        door2.rotateAngleY = -0.08726647F;
        door2.rotateAngleZ = 0F;
        door2.renderWithRotation(rotation);

        knob2.rotateAngleX = 0F;
        knob2.rotateAngleY = -0.08726647F;
        knob2.rotateAngleZ = 0F;
        knob2.renderWithRotation(rotation);

        hook.rotateAngleX = 0F;
        hook.rotateAngleY = 0F;
        hook.rotateAngleZ = 0F;
        hook.renderWithRotation(rotation);

        saw2.rotateAngleX = 0F;
        saw2.rotateAngleY = 0F;
        saw2.rotateAngleZ = 0F;
        saw2.renderWithRotation(rotation);

        saw3.rotateAngleX = 0F;
        saw3.rotateAngleY = 0F;
        saw3.rotateAngleZ = 0F;
        saw3.renderWithRotation(rotation);

        saw4.rotateAngleX = 0F;
        saw4.rotateAngleY = 0F;
        saw4.rotateAngleZ = 0F;
        saw4.renderWithRotation(rotation);

        saw5.rotateAngleX = 0F;
        saw5.rotateAngleY = 0F;
        saw5.rotateAngleZ = 0F;
        saw5.renderWithRotation(rotation);

        saw6.rotateAngleX = 0F;
        saw6.rotateAngleY = 0F;
        saw6.rotateAngleZ = 0F;
        saw6.renderWithRotation(rotation);

        saw7.rotateAngleX = 0F;
        saw7.rotateAngleY = 0F;
        saw7.rotateAngleZ = 0F;
        saw7.renderWithRotation(rotation);

        saw8.rotateAngleX = 0F;
        saw8.rotateAngleY = 0F;
        saw8.rotateAngleZ = 0F;
        saw8.renderWithRotation(rotation);

        saw.rotateAngleX = 0F;
        saw.rotateAngleY = 0F;
        saw.rotateAngleZ = 0F;
        saw.renderWithRotation(rotation);

        hammer1.rotateAngleX = 0F;
        hammer1.rotateAngleY = 0F;
        hammer1.rotateAngleZ = 0F;
        hammer1.renderWithRotation(rotation);

        hammer2.rotateAngleX = 0F;
        hammer2.rotateAngleY = 0F;
        hammer2.rotateAngleZ = 0F;
        hammer2.renderWithRotation(rotation);

        hammer3.rotateAngleX = 0F;
        hammer3.rotateAngleY = 0F;
        hammer3.rotateAngleZ = 0F;
        hammer3.renderWithRotation(rotation);

        hammer4.rotateAngleX = 0F;
        hammer4.rotateAngleY = 0F;
        hammer4.rotateAngleZ = 0F;
        hammer4.renderWithRotation(rotation);

        hammer5.rotateAngleX = 0F;
        hammer5.rotateAngleY = 0F;
        hammer5.rotateAngleZ = 0F;
        hammer5.renderWithRotation(rotation);

        hammer6.rotateAngleX = 0F;
        hammer6.rotateAngleY = 0F;
        hammer6.rotateAngleZ = 0F;
        hammer6.renderWithRotation(rotation);

        hammer7.rotateAngleX = 0F;
        hammer7.rotateAngleY = 0F;
        hammer7.rotateAngleZ = 0F;
        hammer7.renderWithRotation(rotation);

        hook2.rotateAngleX = 0F;
        hook2.rotateAngleY = 0F;
        hook2.rotateAngleZ = 0F;
        hook2.renderWithRotation(rotation);

        hook3.rotateAngleX = 0F;
        hook3.rotateAngleY = 0F;
        hook3.rotateAngleZ = 0F;
        hook3.renderWithRotation(rotation);

        escuadra.rotateAngleX = 0F;
        escuadra.rotateAngleY = 0F;
        escuadra.rotateAngleZ = 0F;
        escuadra.renderWithRotation(rotation);

        escuadra2.rotateAngleX = 0F;
        escuadra2.rotateAngleY = 0F;
        escuadra2.rotateAngleZ = 0F;
        escuadra2.renderWithRotation(rotation);

        hook4.rotateAngleX = 0F;
        hook4.rotateAngleY = 0F;
        hook4.rotateAngleZ = 0F;
        hook4.renderWithRotation(rotation);

        press.rotateAngleX = 0F;
        press.rotateAngleY = 0F;
        press.rotateAngleZ = 0F;
        press.renderWithRotation(rotation);

        wheel1.rotateAngleX = 0F;
        wheel1.rotateAngleY = 0F;
        wheel1.rotateAngleZ = 0F;
        wheel1.renderWithRotation(rotation);

        wheel1m1.rotateAngleX = 0F;
        wheel1m1.rotateAngleY = 0F;
        wheel1m1.rotateAngleZ = 1.047197F;
        wheel1m1.renderWithRotation(rotation);

        wheel1m2.rotateAngleX = -6.283185F;
        wheel1m2.rotateAngleY = 0F;
        wheel1m2.rotateAngleZ = 2.094395F;
        wheel1m2.renderWithRotation(rotation);

        wheel1m3.rotateAngleX = 0F;
        wheel1m3.rotateAngleY = 0F;
        wheel1m3.rotateAngleZ = -1.047197F;
        wheel1m3.renderWithRotation(rotation);

        wheel1m4.rotateAngleX = 0F;
        wheel1m4.rotateAngleY = 0F;
        wheel1m4.rotateAngleZ = -2.094395F;
        wheel1m4.renderWithRotation(rotation);

        wheel1m5.rotateAngleX = 0F;
        wheel1m5.rotateAngleY = 0F;
        wheel1m5.rotateAngleZ = -3.141593F;
        wheel1m5.renderWithRotation(rotation);

        wheel1screw2.rotateAngleX = 0.1718548F;
        wheel1screw2.rotateAngleY = 0.3053495F;
        wheel1screw2.rotateAngleZ = 2.644501F;
        wheel1screw2.renderWithRotation(rotation);

        wheel1screw1.rotateAngleX = 0.1718547F;
        wheel1screw1.rotateAngleY = -0.3053496F;
        wheel1screw1.rotateAngleZ = 0.4970914F;
        wheel1screw1.renderWithRotation(rotation);

        wheel1screw3.rotateAngleX = -0.3490658F;
        wheel1screw3.rotateAngleY = -1.557684E-08F;
        wheel1screw3.rotateAngleZ = -1.570796F;
        wheel1screw3.renderWithRotation(rotation);

        wheel1screw.rotateAngleX = 0F;
        wheel1screw.rotateAngleY = 0F;
        wheel1screw.rotateAngleZ = 0F;
        wheel1screw.renderWithRotation(rotation);

        wheel2.rotateAngleX = 0F;
        wheel2.rotateAngleY = 0F;
        wheel2.rotateAngleZ = 0F;
        wheel2.renderWithRotation(rotation);

        wheel2m1.rotateAngleX = 0F;
        wheel2m1.rotateAngleY = 0F;
        wheel2m1.rotateAngleZ = 1.047197F;
        wheel2m1.renderWithRotation(rotation);

        wheel2m2.rotateAngleX = -6.283185F;
        wheel2m2.rotateAngleY = 0F;
        wheel2m2.rotateAngleZ = 2.094395F;
        wheel2m2.renderWithRotation(rotation);

        wheel2m3.rotateAngleX = 0F;
        wheel2m3.rotateAngleY = 0F;
        wheel2m3.rotateAngleZ = -1.047197F;
        wheel2m3.renderWithRotation(rotation);

        wheel2m4.rotateAngleX = 0F;
        wheel2m4.rotateAngleY = 0F;
        wheel2m4.rotateAngleZ = -2.094395F;
        wheel2m4.renderWithRotation(rotation);

        wheel2m5.rotateAngleX = 0F;
        wheel2m5.rotateAngleY = 0F;
        wheel2m5.rotateAngleZ = -3.141593F;
        wheel2m5.renderWithRotation(rotation);

        wheel2screw2.rotateAngleX = 0.1718548F;
        wheel2screw2.rotateAngleY = 0.3053495F;
        wheel2screw2.rotateAngleZ = 2.644501F;
        wheel2screw2.renderWithRotation(rotation);

        wheel2screw1.rotateAngleX = 0.1718547F;
        wheel2screw1.rotateAngleY = -0.3053496F;
        wheel2screw1.rotateAngleZ = 0.4970914F;
        wheel2screw1.renderWithRotation(rotation);

        wheel2screw3.rotateAngleX = -0.3490658F;
        wheel2screw3.rotateAngleY = -1.557684E-08F;
        wheel2screw3.rotateAngleZ = -1.570796F;
        wheel2screw3.renderWithRotation(rotation);

        wheel2screw.rotateAngleX = 0F;
        wheel2screw.rotateAngleY = 0F;
        wheel2screw.rotateAngleZ = 0F;
        wheel2screw.renderWithRotation(rotation);

        upper.rotateAngleX = 0F;
        upper.rotateAngleY = 0F;
        upper.rotateAngleZ = 0F;
        upper.renderWithRotation(rotation);

        upper1.rotateAngleX = 0F;
        upper1.rotateAngleY = 0F;
        upper1.rotateAngleZ = 0F;
        upper1.renderWithRotation(rotation);

        upper2.rotateAngleX = 0F;
        upper2.rotateAngleY = 0F;
        upper2.rotateAngleZ = 0F;
        upper2.renderWithRotation(rotation);

        upper2m1.rotateAngleX = 0F;
        upper2m1.rotateAngleY = 0F;
        upper2m1.rotateAngleZ = 0F;
        upper2m1.renderWithRotation(rotation);

        upper2m2.rotateAngleX = 0.7853982F;
        upper2m2.rotateAngleY = 0F;
        upper2m2.rotateAngleZ = 0F;
        upper2m2.renderWithRotation(rotation);

        upper3.rotateAngleX = 0F;
        upper3.rotateAngleY = 0F;
        upper3.rotateAngleZ = 0F;
        upper3.renderWithRotation(rotation);

        upper4.rotateAngleX = 0F;
        upper4.rotateAngleY = 0F;
        upper4.rotateAngleZ = 0F;
        upper4.renderWithRotation(rotation);

        upper5.rotateAngleX = 0F;
        upper5.rotateAngleY = 0F;
        upper5.rotateAngleZ = 0F;
        upper5.renderWithRotation(rotation);

        upper9.rotateAngleX = 0F;
        upper9.rotateAngleY = 0F;
        upper9.rotateAngleZ = 0F;
        upper9.renderWithRotation(rotation);

        upperC1.rotateAngleX = 0F;
        upperC1.rotateAngleY = 0F;
        upperC1.rotateAngleZ = 0F;
        upperC1.renderWithRotation(rotation);

        knob3.rotateAngleX = 0F;
        knob3.rotateAngleY = 0F;
        knob3.rotateAngleZ = 0F;
        knob3.renderWithRotation(rotation);

        upper6.rotateAngleX = 0F;
        upper6.rotateAngleY = 0F;
        upper6.rotateAngleZ = 0F;
        upper6.renderWithRotation(rotation);

        upper6m1.rotateAngleX = 0F;
        upper6m1.rotateAngleY = 0F;
        upper6m1.rotateAngleZ = 0F;
        upper6m1.renderWithRotation(rotation);

        upper6m2.rotateAngleX = 0.7853982F;
        upper6m2.rotateAngleY = 0F;
        upper6m2.rotateAngleZ = 0F;
        upper6m2.renderWithRotation(rotation);

        upper7.rotateAngleX = 0F;
        upper7.rotateAngleY = 0F;
        upper7.rotateAngleZ = 0F;
        upper7.renderWithRotation(rotation);

        upper7m1.rotateAngleX = 0F;
        upper7m1.rotateAngleY = 0F;
        upper7m1.rotateAngleZ = 0F;
        upper7m1.renderWithRotation(rotation);

        upper7m2.rotateAngleX = 0.7853982F;
        upper7m2.rotateAngleY = 0F;
        upper7m2.rotateAngleZ = 0F;
        upper7m2.renderWithRotation(rotation);

        upper8.rotateAngleX = 0F;
        upper8.rotateAngleY = 0F;
        upper8.rotateAngleZ = 0F;
        upper8.renderWithRotation(rotation);

        upper8m1.rotateAngleX = 0F;
        upper8m1.rotateAngleY = 0F;
        upper8m1.rotateAngleZ = 0F;
        upper8m1.renderWithRotation(rotation);

        upper8m2.rotateAngleX = 0.7853982F;
        upper8m2.rotateAngleY = 0F;
        upper8m2.rotateAngleZ = 0F;
        upper8m2.renderWithRotation(rotation);

        cajon5.rotateAngleX = 0F;
        cajon5.rotateAngleY = 0F;
        cajon5.rotateAngleZ = 0F;
        cajon5.renderWithRotation(rotation);

        knob4.rotateAngleX = 0F;
        knob4.rotateAngleY = 0F;
        knob4.rotateAngleZ = 0F;
        knob4.renderWithRotation(rotation);

        C5m1.rotateAngleX = 0F;
        C5m1.rotateAngleY = 0F;
        C5m1.rotateAngleZ = 0F;
        C5m1.renderWithRotation(rotation);

        C5m2.rotateAngleX = 0F;
        C5m2.rotateAngleY = 0F;
        C5m2.rotateAngleZ = 0F;
        C5m2.renderWithRotation(rotation);

        C5m3.rotateAngleX = 0F;
        C5m3.rotateAngleY = 0F;
        C5m3.rotateAngleZ = 0F;
        C5m3.renderWithRotation(rotation);

        C5m5.rotateAngleX = 0F;
        C5m5.rotateAngleY = 0F;
        C5m5.rotateAngleZ = 0F;
        C5m5.renderWithRotation(rotation);

        C5m4.rotateAngleX = 0F;
        C5m4.rotateAngleY = 0F;
        C5m4.rotateAngleZ = 0F;
        C5m4.renderWithRotation(rotation);

        C5m6.rotateAngleX = 0F;
        C5m6.rotateAngleY = 0F;
        C5m6.rotateAngleZ = 0F;
        C5m6.renderWithRotation(rotation);

        cajon5a1.rotateAngleX = 0F;
        cajon5a1.rotateAngleY = 0F;
        cajon5a1.rotateAngleZ = 0F;
        cajon5a1.renderWithRotation(rotation);

        cajon6.rotateAngleX = 0F;
        cajon6.rotateAngleY = 0F;
        cajon6.rotateAngleZ = 0F;
        cajon6.renderWithRotation(rotation);

        knob5.rotateAngleX = 0F;
        knob5.rotateAngleY = 0F;
        knob5.rotateAngleZ = 0F;
        knob5.renderWithRotation(rotation);

        cajon7.rotateAngleX = 0F;
        cajon7.rotateAngleY = 0F;
        cajon7.rotateAngleZ = 0F;
        cajon7.renderWithRotation(rotation);

        knob6.rotateAngleX = 0F;
        knob6.rotateAngleY = 0F;
        knob6.rotateAngleZ = 0F;
        knob6.renderWithRotation(rotation);

        upper91.rotateAngleX = 0F;
        upper91.rotateAngleY = 0F;
        upper91.rotateAngleZ = 0F;
        upper91.renderWithRotation(rotation);

        upper10.rotateAngleX = 0F;
        upper10.rotateAngleY = 0F;
        upper10.rotateAngleZ = 0F;
        upper10.renderWithRotation(rotation);

        upper11.rotateAngleX = 0F;
        upper11.rotateAngleY = 0F;
        upper11.rotateAngleZ = 0F;
        upper11.renderWithRotation(rotation);

    }

}
