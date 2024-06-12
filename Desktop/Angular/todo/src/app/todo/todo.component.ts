import { Component } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Todo } from '../list-todos/list-todos.component';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, NgModel } from '@angular/forms';
import { DatePipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [FormsModule, DatePipe, NgIf],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css',
})
export class TodoComponent {
  id: number = 0;
  todo: Todo = new Todo(this.id, 'Szymon', '', false, new Date());

  constructor(
    private todoService: TodoDataService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    if (this.id != 0) {
      this.todoService
        .retriveTodo('Szymon', this.id)
        .subscribe((data) => (this.todo = data));
    }
  }

  saveTodo() {
    if (this.id === 0) {
      this.todoService.createNewTodo('Szymon', this.todo).subscribe((data) => {
        console.log(data);
        this.router.navigate(['/todos']);
      });
    } else {
      this.todoService
        .updateTodo('Szymon', this.id, this.todo)
        .subscribe((data) => {
          console.log(data);
          this.router.navigate(['/todos']);
        });
    }
  }
}
