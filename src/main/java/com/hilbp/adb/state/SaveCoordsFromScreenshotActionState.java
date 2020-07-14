package com.hilbp.adb.state;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hilbp.adb.entity.Coord;

/**
 * 从界面截图中通过图像识别获取坐标，并保存Coord
 * @author hilbp
 *
 */
@Component
public class SaveCoordsFromScreenshotActionState extends AbstractActionState<List<Coord>> {}
