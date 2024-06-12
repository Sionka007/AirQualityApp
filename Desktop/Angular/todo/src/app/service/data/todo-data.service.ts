import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from '../../list-todos/list-todos.component';
import { JPA_API_URL } from '../../app.constansts';

@Injectable({
  providedIn: 'root',
})
export class TodoDataService {
  constructor(private http: HttpClient) {}

  retriveAllTodo(username: any) {
    return this.http.get<Todo[]>(`${JPA_API_URL}/users/${username}/todos`);
  }

  retriveTodo(username: any, id: number) {
    return this.http.get<Todo>(`${JPA_API_URL}/users/${username}/todos/${id}`);
  }

  deleteTodo(username: string, id: number) {
    return this.http.delete(`${JPA_API_URL}/users/${username}/todos/${id}`);
  }

  updateTodo(username: string, id: number, todo: Todo) {
    return this.http.put<Todo>(
      `${JPA_API_URL}/users/${username}/todos/${id}`,
      todo
    );
  }

  createNewTodo(username: string, todo: Todo) {
    return this.http.post<Todo>(`${JPA_API_URL}/users/${username}/todos`, todo);
  }
}
