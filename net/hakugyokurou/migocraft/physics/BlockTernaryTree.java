package net.hakugyokurou.migocraft.physics;

/**
 * BlockTernaryTree(砖块三叉树)是一种快速搜索可用砖块的类.
 * </br>2013.6.30
 * </br><b>已作废!三叉树被证实为无法有效处理特殊情况.</b>
 * @author szszss
 */
@Deprecated
public class BlockTernaryTree {
	
	/**
	 * 五叉根节点
	 * @author szszss
	 */
	public class BttRootQuintet extends BttRoot
	{
		private BttBranch northBranch;
		private BttBranch southBranch;
		private BttBranch westBranch;
		private BttBranch eastBranch;
		private BttBranch perpendicularBranch;
		
	}
	
	/**
	 * 二叉根节点
	 * @author szszss
	 */
	public class BttRootBinary extends BttRoot
	{
		private BttBranch LateralBranch;
		private BttBranch VerticalBranch;
		
	}
	
	public abstract class BttRoot implements IBttParent
	{
		@Override
		public boolean isRoot() 
		{
			return true;
		}
		
		@Override
		public short getDepth() {
			return 0;
		}
	}
	
	/**
	 * 横向枝杈类
	 * @author szszss
	 */
	public class BttBranchLateral extends BttBranch
	{
		private BttBranch leftBranch;
		private BttBranch midBranch;
		private BttBranch rightBranch;

		/**
		 * 等同于获取左枝杈
		 * Be equivalent to getLeftBranch
		 */
		@Override
		public BttBranch getChild1() {
			return leftBranch;
		}

		/**
		 * 等同于获取中枝杈
		 * Be equivalent to getMiddleBranch
		 */
		@Override
		public BttBranch getChild2() {
			return midBranch;
		}

		/**
		 * 等同于获取右枝杈
		 * Be equivalent to getRightBranch
		 */
		@Override
		public BttBranch getChild3() {
			return rightBranch;
		}
		
		public BttBranch getLeftBranch()
		{
			return leftBranch;
		}
		
		public BttBranch getMiddleBranch()
		{
			return midBranch;
		}
		
		public BttBranch getRightBranch()
		{
			return rightBranch;
		}		
	}
	
	/**
	 * 竖向枝杈类
	 * @author szszss
	 */
	public class BttBranchVertical extends BttBranch
	{
		private BttBranch upBranch;
		private BttBranch midBranch;
		private BttBranch downBranch;

		/**
		 * 等同于获取上枝杈
		 * Be equivalent to getUpBranch
		 */
		@Override
		public BttBranch getChild1() {
			return upBranch;
		}

		/**
		 * 等同于获取中枝杈
		 * Be equivalent to getMiddleBranch
		 */
		@Override
		public BttBranch getChild2() {
			return midBranch;
		}

		/**
		 * 等同于获取下枝杈
		 * Be equivalent to getDownBranch
		 */
		@Override
		public BttBranch getChild3() {
			return downBranch;
		}
		
		public BttBranch getUpBranch()
		{
			return upBranch;
		}
		
		public BttBranch getMiddleBranch()
		{
			return midBranch;
		}
		
		public BttBranch getDownBranch()
		{
			return downBranch;
		}		
	}
	
	/**
	 * 枝杈类,这个类是个抽象类,因为在实际创建时需要明确枝杈的朝向.(横或竖)
	 * @author szszss
	 */
	public abstract class BttBranch implements IBttParent
	{
		protected IBttParent parent; 
		protected short depth;
		
		public abstract BttBranch getChild1();
		public abstract BttBranch getChild2();
		public abstract BttBranch getChild3();
		
		@Override
		public boolean isRoot() 
		{
			return true;
		}
		
		@Override
		public short getDepth() {
			return depth;
		}
	}
	
	/**
	 * 父枝接口,无论是根还是杈,都实现了这个接口,因为它们都有可能成为父杈.
	 * @author szszss
	 */
	private interface IBttParent
	{
		public abstract boolean isRoot();
		public abstract short getDepth();
	}

}
