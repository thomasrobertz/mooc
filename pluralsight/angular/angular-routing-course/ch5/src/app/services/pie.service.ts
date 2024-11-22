import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, map, of, switchMap, tap } from 'rxjs';
import { Category, Pie } from '../models/pie';
import { PIES } from '../models/pie-data.mock';
import { toSignal } from '@angular/core/rxjs-interop';
import { ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PieService {
  readonly pies$ = of(PIES);
  readonly piesSignal = toSignal(this.pies$, {initialValue: []});

  private readonly selectedCategory = new BehaviorSubject<string>(Category.ALL);
  readonly selectedCategory$ = this.selectedCategory.asObservable();

  readonly activatedRoute = inject(ActivatedRoute);
  private readonly selectedPie = this.activatedRoute.queryParamMap
    .pipe(map(p => p.get('productId')));

  readonly filteredPies$ = this.selectedCategory.pipe(
    switchMap((category) => this.pies$.pipe(
      map((pies) => {
        if(category === Category.ALL) {
          return pies;
        }

        return pies.filter((pie: Pie) => pie.category === category);
      }),
    ))
  );
  readonly filteredPieSignal = toSignal(this.filteredPies$, {initialValue: []});

  readonly featuredPies$ = this.pies$.pipe(
    map((pies) => [pies[3], pies[6], pies[17]])
  );
  readonly featuredPiesSignal = toSignal(this.featuredPies$, {initialValue: []});

  readonly selectedPie$ = this.selectedPie.pipe(switchMap((id) =>
    this.filteredPies$.pipe(
      map((pies) => {
        if(id){
          return pies.find((pie) => pie.id === id) || pies[0];
        }

        return pies[0];
      })
    )));
  readonly selectedPieSignal = toSignal(this.selectedPie$);

  setSelectedCategory(category: string) {
    this.selectedCategory.next(category);
  }
}
