import { DatePipe, NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { Component, Pipe } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Router, RouterLink } from '@angular/router';

export class Todo {
  date: any;
  constructor(
    public id: number,
    public username: string,
    public description: string,
    public done: boolean,
    public targetDate: Date
  ) {}
}

@Component({
  selector: 'app-list-todos',
  standalone: true,
  imports: [NgFor, DatePipe, UpperCasePipe, NgIf],
  templateUrl: './list-todos.component.html',
  styleUrl: './list-todos.component.css',
})
export class ListTodosComponent {
  todos: Todo[] = [];
  message: string = '';
  // new Todo(1, 'Learn to dance', false, new Date()),
  // new Todo(2, 'Expert at Angular', false, new Date()),
  // new Todo(3, 'Visit India', false, new Date()),
  // { id: 1, description: 'Learn to dance' },
  // { id: 2, description: 'Expert at Angular' },
  // { id: 3, description: 'Visit India' },

  // todo = {
  //   id: 1,
  //   description: 'Learn to dance',
  // };

  constructor(private todoService: TodoDataService, private router: Router) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.refreshTodos();
  }

  refreshTodos() {
    this.todoService.retriveAllTodo('Szymon').subscribe((response) => {
      console.log(response);
      this.todos = response;
    });
  }

  deleteTodo(id: number) {
    console.log('Delete todo' + id);

    this.todoService.deleteTodo('Szymon', id).subscribe((response) => {
      console.log(response);
      this.message = `Successfully delete ${id}`;
      this.refreshTodos();
    });
  }

  updateTodo(id: number) {
    console.log('Update' + id);
    this.router.navigate(['todos/update', id]);
  }

  createTodo() {
    console.log('Update' + 0);
    this.router.navigate(['todos/update', 0]);
  }
}
