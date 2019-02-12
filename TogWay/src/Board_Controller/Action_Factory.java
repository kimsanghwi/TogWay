package Board_Controller;

public class Action_Factory {

	private static Action_Factory instance = new Action_Factory();
	
	private Action_Factory()
	{
		super();
	}
	
	public static Action_Factory getInstance()
	{
		return instance;
	}
	
	
	public Action getAction(String command)
	{
		Action action = null;
		
		if(command.equals("main_list"))
		{
			action = new MainListAction();
		
		}
		
		// 리스트로 이동 
		if(command.equals("mypet_list"))
		{
			action = new BoardlistAction();
		}
		if(command.equals("review_list"))
		{
			action = new BoardlistAction();
		}
		if(command.equals("fleamarket_list"))
		{
			action = new BoardlistAction();
		}
		if(command.equals("hospital_list"))
		{
			action = new BoardlistAction();
		}
		else if(command.equals("writeform"))
		{
			action = new BoardWriteFormAction();
		}
		else if(command.equals("write"))
		{
			action = new BoardWriteAction();
		}
		else if(command.equals("boardview"))
		{
			action = new BoardViewAction();
		}
		else if(command.equals("board_check_pass_form"))
		{
			action = new BoardCheckPassFormAction();
		}
		else if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		}else if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		}else if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		}else if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		}

		
		
		return action;
	}
			
}
