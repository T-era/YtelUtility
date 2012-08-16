package ytel.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorderLayoutBuilder {
	public static enum Direction {
		North(BorderLayout.NORTH),
		West(BorderLayout.WEST),
		East(BorderLayout.EAST),
		South(BorderLayout.SOUTH),
		Center(BorderLayout.CENTER);

		private String borderLayoutString;
		Direction(String borderLayoutString) {
			this.borderLayoutString = borderLayoutString;
		}
	}

	private final Map<Direction, BorderLayoutBuilder> componentMap;
	private final List<Component> content;
	private boolean orientationHorizonal;

	public void setOrientationHorizonal(boolean orientationHorizonal) {
		this.orientationHorizonal = orientationHorizonal;
	}
	public BorderLayoutBuilder() {
		this.content = new ArrayList<Component>();
		this.componentMap = new HashMap<BorderLayoutBuilder.Direction, BorderLayoutBuilder>();
	}
	protected BorderLayoutBuilder(List<Component> content) {
		this.content = content;
		this.componentMap = new HashMap<BorderLayoutBuilder.Direction, BorderLayoutBuilder>();
	}

	public void add(Component arg, Direction... directions) {
		add(arg, false, directions);
	}
	public void add(Component arg, boolean orientationHorizonal, Direction... directions) {
		if (directions.length == 0) {
			this.orientationHorizonal = orientationHorizonal;
			content.add(arg);
		}else {
			Direction dir = directions[0];
			if (componentMap.get(dir) == null) {
				BorderLayoutBuilder blb;
				if (dir == Direction.Center) {
					blb = new BorderLayoutBuilder(this.content);
				} else {
					blb = new BorderLayoutBuilder();
				}
				blb.add(arg, orientationHorizonal, cdr(directions));
				componentMap.put(dir, blb);
			} else {
				BorderLayoutBuilder blb = componentMap.get(dir);
				blb.add(arg, orientationHorizonal, cdr(directions));
			}
		}
	}

	public void layoutComponent(Container container) {
		container.setLayout(new BorderLayout());

		if (componentMap.get(Direction.Center) == null) {
			int mainSize = this.content.size();
			if (mainSize > 1) {
				Container center = new Container();
				if (orientationHorizonal) {
					center.setLayout(new GridLayout(mainSize, 1));
				} else {
					center.setLayout(new GridLayout(1, mainSize));
				}
				for (Component element : this.content) {
					center.add(element);
				}
				container.add(center, BorderLayout.CENTER);
			} else if (mainSize == 1) {
				container.add(this.content.get(0), BorderLayout.CENTER);
			}
		} else {
			setContainerInDirection(container, Direction.Center);
		}
		setContainerInDirection(container, Direction.North);
		setContainerInDirection(container, Direction.South);
		setContainerInDirection(container, Direction.East);
		setContainerInDirection(container, Direction.West);
	}
	private void setContainerInDirection(Container container, Direction d) {
		if (componentMap.get(d) != null) {
			Container temp = new Container();
			componentMap.get(d).layoutComponent(temp);
			container.add(temp, d.borderLayoutString);
		}
	}

	private static Direction[] cdr(Direction[] arg) {
		Direction[] ret = new Direction[arg.length - 1];
		for (int i = 1; i < arg.length; i ++) {
			ret[i-1] = arg[i];
		}
		return ret;
	}
}