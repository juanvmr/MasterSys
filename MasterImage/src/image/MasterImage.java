package image;

import javax.swing.*;

public class MasterImage extends ImageIcon {

	public static final MasterImage alterar_16x16 = LoadImage("16x16/alterar.png");
	public static final MasterImage aplicacao_16x16 = LoadImage("16x16/aplicacao.png");

	private MasterImage (final String iconTitle) {
		super(MasterImage.class.getResource(iconTitle));
	}

	public static MasterImage LoadImage(final String iconTitle) {
		return new MasterImage(iconTitle);
	}

}
